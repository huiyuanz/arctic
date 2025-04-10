/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.netease.arctic.io;

import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.netease.arctic.BasicTableTestHelper;
import com.netease.arctic.ams.api.properties.TableFormat;
import com.netease.arctic.catalog.BasicCatalogTestHelper;
import com.netease.arctic.catalog.TableTestBase;
import com.netease.arctic.data.file.DataFileWithSequence;
import com.netease.arctic.data.file.DeleteFileWithSequence;
import com.netease.arctic.io.reader.GenericCombinedIcebergDataReader;
import com.netease.arctic.scan.CombinedIcebergScanTask;
import org.apache.iceberg.DataFile;
import org.apache.iceberg.DeleteFile;
import org.apache.iceberg.FileFormat;
import org.apache.iceberg.Schema;
import org.apache.iceberg.StructLike;
import org.apache.iceberg.TableProperties;
import org.apache.iceberg.TestHelpers;
import org.apache.iceberg.data.FileHelpers;
import org.apache.iceberg.data.GenericRecord;
import org.apache.iceberg.data.IdentityPartitionConverters;
import org.apache.iceberg.data.Record;
import org.apache.iceberg.io.CloseableIterable;
import org.apache.iceberg.io.OutputFileFactory;
import org.apache.iceberg.relocated.com.google.common.collect.Lists;
import org.apache.iceberg.types.TypeUtil;
import org.apache.iceberg.util.Pair;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RunWith(Parameterized.class)
public class TestGenericCombinedIcebergDataReader extends TableTestBase {

  private final FileFormat fileFormat;

  private CombinedIcebergScanTask scanTask;
  private CombinedIcebergScanTask dataScanTask;
  private GenericCombinedIcebergDataReader dataReader;

  public TestGenericCombinedIcebergDataReader(
      boolean partitionedTable, FileFormat fileFormat) {
    super(new BasicCatalogTestHelper(TableFormat.ICEBERG),
        new BasicTableTestHelper(false, partitionedTable, buildTableProperties(fileFormat)));
    this.fileFormat = fileFormat;
  }

  @Parameterized.Parameters(name = "partitionedTable = {0}, fileFormat = {1}")
  public static Object[][] parameters() {
    return new Object[][] {{true, FileFormat.PARQUET}, {false, FileFormat.PARQUET},
                           {true, FileFormat.AVRO}, {false, FileFormat.AVRO},
                           {true, FileFormat.ORC}, {false, FileFormat.ORC}};
  }

  private static Map<String, String> buildTableProperties(FileFormat fileFormat) {
    Map<String, String> tableProperties = Maps.newHashMapWithExpectedSize(3);
    tableProperties.put(TableProperties.FORMAT_VERSION, "2");
    tableProperties.put(TableProperties.DEFAULT_FILE_FORMAT, fileFormat.name());
    tableProperties.put(TableProperties.DELETE_DEFAULT_FILE_FORMAT, fileFormat.name());
    return tableProperties;
  }

  private StructLike getPartitionData() {
    if (isPartitionedTable()) {
      return TestHelpers.Row.of(0);
    } else {
      return TestHelpers.Row.of();
    }
  }

  @Before
  public void initDataAndReader() throws IOException {
    StructLike partitionData = getPartitionData();
    OutputFileFactory outputFileFactory = OutputFileFactory.builderFor(getArcticTable().asUnkeyedTable(), 0, 1)
        .format(fileFormat).build();
    DataFile dataFile = FileHelpers.writeDataFile(getArcticTable().asUnkeyedTable(),
        outputFileFactory.newOutputFile(partitionData).encryptingOutputFile(), partitionData,
        Arrays.asList(
            DataTestHelpers.createRecord(1, "john", 0, "1970-01-01T08:00:00"),
            DataTestHelpers.createRecord(2, "lily", 1, "1970-01-01T08:00:00"),
            DataTestHelpers.createRecord(3, "sam", 2, "1970-01-01T08:00:00")));

    Schema idSchema = TypeUtil.select(BasicTableTestHelper.TABLE_SCHEMA, Sets.newHashSet(1));
    GenericRecord idRecord = GenericRecord.create(idSchema);
    DeleteFile eqDeleteFile = FileHelpers.writeDeleteFile(getArcticTable().asUnkeyedTable(),
        outputFileFactory.newOutputFile(partitionData).encryptingOutputFile(), partitionData,
        Collections.singletonList(idRecord.copy("id", 1)), idSchema);

    List<Pair<CharSequence, Long>> deletes = Lists.newArrayList();
    deletes.add(Pair.of(dataFile.path(), 1L));
    DeleteFile posDeleteFile = FileHelpers.writeDeleteFile(getArcticTable().asUnkeyedTable(),
        outputFileFactory.newOutputFile(partitionData).encryptingOutputFile(), partitionData, deletes).first();

    scanTask = new CombinedIcebergScanTask(
        new DataFileWithSequence[] {new DataFileWithSequence(dataFile, 1L)},
        new DeleteFileWithSequence[] {new DeleteFileWithSequence(eqDeleteFile, 2L),
            new DeleteFileWithSequence(posDeleteFile, 3L)},
        getArcticTable().spec(), partitionData);

    dataScanTask = new CombinedIcebergScanTask(
        new DataFileWithSequence[] {new DataFileWithSequence(dataFile, 1L)},
        new DeleteFileWithSequence[] {}, getArcticTable().spec(), partitionData);

    dataReader = new GenericCombinedIcebergDataReader(getArcticTable().io(), getArcticTable().schema(),
        getArcticTable().schema(), null, false,
        IdentityPartitionConverters::convertConstant, false);
  }

  @Test
  public void readAllData() throws IOException {
    try (CloseableIterable<Record> records = dataReader.readData(scanTask)) {
      Assert.assertEquals(1, Iterables.size(records));
      Record record = Iterables.getFirst(records, null);
      Assert.assertEquals(record.get(0), 3);
    }
  }

  @Test
  public void readAllDataNegate() throws IOException {
    try (CloseableIterable<Record> records = dataReader.readDeleteData(scanTask)) {
      Assert.assertEquals(2, Iterables.size(records));
      Record first = Iterables.getFirst(records, null);
      Assert.assertEquals(first.get(0), 1);
      Record last = Iterables.getLast(records);
      Assert.assertEquals(last.get(0), 2);
    }
  }

  @Test
  public void readOnlyData() throws IOException {
    try (CloseableIterable<Record> records = dataReader.readData(dataScanTask)) {
      Assert.assertEquals(3, Iterables.size(records));
    }
  }

  @Test
  public void readOnlyDataNegate() throws IOException {
    try (CloseableIterable<Record> records = dataReader.readDeleteData(dataScanTask)) {
      Assert.assertEquals(0, Iterables.size(records));
    }
  }
}
