/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *  *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *  *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.netease.arctic.ams.server.optimize;

import com.netease.arctic.ams.api.OptimizeType;
import com.netease.arctic.ams.server.model.OptimizeHistory;
import com.netease.arctic.table.ArcticTable;
import com.netease.arctic.table.KeyedTable;
import com.netease.arctic.table.TableIdentifier;
import com.netease.arctic.table.TableProperties;
import com.netease.arctic.table.UnkeyedTable;
import org.apache.commons.lang3.RandomUtils;
import org.apache.iceberg.AppendFiles;
import org.apache.iceberg.DataFile;
import org.apache.iceberg.data.Record;
import org.apache.iceberg.relocated.com.google.common.collect.Lists;

import java.util.List;

public class MixedIcebergOptimizingTest extends AbstractOptimizingTest {
  private final ArcticTable arcticTable;
  private final long startId;

  public MixedIcebergOptimizingTest(ArcticTable arcticTable, long startId) {
    this.arcticTable = arcticTable;
    this.startId = startId;
  }

  public void testKeyedTableContinueOptimizing() {
    int offset = 1;
    KeyedTable table = arcticTable.asKeyedTable();
    TableIdentifier tb = table.id();
    emptyCommit(table);
    emptyCommit(table);
    emptyCommit(table);
    emptyCommit(table);
    // Step1: insert change data
    writeChange(table, Lists.newArrayList(
        newRecord(3, "aaa", quickDateWithZone(3)),
        newRecord(4, "bbb", quickDateWithZone(3)),
        newRecord(5, "eee", quickDateWithZone(4)),
        newRecord(6, "ddd", quickDateWithZone(4))
    ), null);

    // wait Minor Optimize result, no major optimize because there is only 1 base file for each node
    OptimizeHistory optimizeHistory = waitOptimizeResult(tb, startId + offset++);
    assertOptimizeHistory(optimizeHistory, OptimizeType.Minor, 4, 4);
    assertIds(readRecords(table), 3, 4, 5, 6);

    // Step2: insert change data
    writeChange(table, Lists.newArrayList(
        newRecord(7, "fff", quickDateWithZone(3)),
        newRecord(8, "ggg", quickDateWithZone(3)),
        newRecord(9, "hhh", quickDateWithZone(4)),
        newRecord(10, "iii", quickDateWithZone(4))
    ), null);

    // wait Minor/Major Optimize result
    optimizeHistory = waitOptimizeResult(tb, startId + offset++);
    assertOptimizeHistory(optimizeHistory, OptimizeType.Minor, 8, 4);
    optimizeHistory = waitOptimizeResult(tb, startId + offset++);
    assertOptimizeHistory(optimizeHistory, OptimizeType.Major, 8, 4);
    assertIds(readRecords(table), 3, 4, 5, 6, 7, 8, 9, 10);

    // Step3: delete change data
    writeChange(table, null, Lists.newArrayList(
        newRecord(7, "fff", quickDateWithZone(3)),
        newRecord(8, "ggg", quickDateWithZone(3))
    ));
    // wait Minor/Major Optimize result
    optimizeHistory = waitOptimizeResult(tb, startId + offset++);
    assertOptimizeHistory(optimizeHistory, OptimizeType.Minor, 4, 2);
    assertIds(readRecords(table), 3, 4, 5, 6, 9, 10);

    // Step4: update change data
    writeChange(table, Lists.newArrayList(
        newRecord(9, "hhh_new", quickDateWithZone(4)),
        newRecord(10, "iii_new", quickDateWithZone(4))
    ), Lists.newArrayList(
        newRecord(9, "fff", quickDateWithZone(4)),
        newRecord(10, "ggg", quickDateWithZone(4))
    ));
    // wait Minor/Major Optimize result
    optimizeHistory = waitOptimizeResult(tb, startId + offset++);
    assertOptimizeHistory(optimizeHistory, OptimizeType.Minor, 6, 4);
    optimizeHistory = waitOptimizeResult(tb, startId + offset++);
    assertOptimizeHistory(optimizeHistory, OptimizeType.Major, 6, 2);
    assertIds(readRecords(table), 3, 4, 5, 6, 9, 10);
    assertNames(readRecords(table), "aaa", "bbb", "eee", "ddd", "hhh_new", "iii_new");

    // Step5: delete all change data
    writeChange(table, null, Lists.newArrayList(
        newRecord(3, "aaa", quickDateWithZone(3)),
        newRecord(4, "bbb", quickDateWithZone(3)),
        newRecord(5, "eee", quickDateWithZone(4)),
        newRecord(6, "ddd", quickDateWithZone(4)),
        newRecord(9, "hhh_new", quickDateWithZone(4)),
        newRecord(10, "iii_new", quickDateWithZone(4))
    ));
    // wait Minor/Major Optimize result
    optimizeHistory = waitOptimizeResult(tb, startId + offset++);
    assertOptimizeHistory(optimizeHistory, OptimizeType.Minor, 10, 4);
    assertIds(readRecords(table));

    // Step6: insert change data
    writeChange(table, Lists.newArrayList(
        newRecord(11, "jjj", quickDateWithZone(3))
    ), null);
    // wait Minor Optimize result, no major optimize because there is only 1 base file for each node
    optimizeHistory = waitOptimizeResult(tb, startId + offset++);
    assertOptimizeHistory(optimizeHistory, OptimizeType.Minor, 3, 1);
    optimizeHistory = waitOptimizeResult(tb, startId + offset++);
    assertOptimizeHistory(optimizeHistory, OptimizeType.Major, 3, 1);
    assertIds(readRecords(table), 11);
    assertOptimizeHangUp(tb, startId + offset);
  }

  public void testPkTableMajorOptimizeLeftPosDelete(){
    int offset = 1;
    KeyedTable table = arcticTable.asKeyedTable();
    TableIdentifier tb = table.id();
    updateProperties(table, TableProperties.ENABLE_SELF_OPTIMIZING, "false");
    // Step1: insert base data
    StringBuilder stringBuilder = new StringBuilder();
    for (int i = 0; i < 100; i++) {
      stringBuilder.append(RandomUtils.nextInt());
    }
    String longString = stringBuilder.toString();
    List<DataFile> dataFiles = writeBase(table, Lists.newArrayList(
        newRecord(1, "aaa" + longString, quickDateWithZone(3)),
        newRecord(5, "bbb" + longString, quickDateWithZone(3)),
        newRecord(9, "ccc" + longString, quickDateWithZone(4)),
        newRecord(13, "ddd" + longString, quickDateWithZone(4))
    ));
    writeBase(table, Lists.newArrayList(
        newRecord(2, "eee" + longString, quickDateWithZone(3)),
        newRecord(6, "fff" + longString, quickDateWithZone(3)),
        newRecord(10, "ggg" + longString, quickDateWithZone(4)),
        newRecord(14, "hhh" + longString, quickDateWithZone(4))
    ));
    updateProperties(table, TableProperties.ENABLE_SELF_OPTIMIZING, "true");
    updateProperties(table, TableProperties.SELF_OPTIMIZING_FULL_TRIGGER_INTERVAL, "1000");

    OptimizeHistory optimizeHistory = waitOptimizeResult(tb, startId + offset++);
    assertOptimizeHistory(optimizeHistory, OptimizeType.FullMajor, 2, 2);
    assertIds(readRecords(table), 1, 5, 9, 13, 2, 6, 10, 14);

    updateProperties(table, TableProperties.ENABLE_SELF_OPTIMIZING, "false");
    updateProperties(table, TableProperties.SELF_OPTIMIZING_FULL_TRIGGER_INTERVAL, "-1");
    long dataFileSize = dataFiles.get(0).fileSizeInBytes();
    updateProperties(table, TableProperties.SELF_OPTIMIZING_FRAGMENT_RATIO,
        TableProperties.SELF_OPTIMIZING_TARGET_SIZE_DEFAULT / (dataFileSize - 100) + "");

    // Step2: insert change data, update 2
    writeChange(table, Lists.newArrayList(
        newRecord(1, "aaa_new", quickDateWithZone(3))
    ), Lists.newArrayList(
        newRecord(1, "aaa" + longString, quickDateWithZone(3))
    ));

    writeChange(table, Lists.newArrayList(
        newRecord(1, "aaa_new2", quickDateWithZone(3))
    ), Lists.newArrayList(
        newRecord(1, "aaa_new", quickDateWithZone(3))
    ));

    writeChange(table, null, Lists.newArrayList(
        newRecord(1, "aaa_new2", quickDateWithZone(3))
    ));

    assertIds(readRecords(table), 5, 9, 13, 2, 6, 10, 14);

    updateProperties(table, TableProperties.ENABLE_SELF_OPTIMIZING, "true");

    // wait Minor Optimize result, no major optimize because there is only 1 base file for each node
    optimizeHistory = waitOptimizeResult(tb, startId + offset++);
    assertOptimizeHistory(optimizeHistory, OptimizeType.Minor, 6, 3);

    optimizeHistory = waitOptimizeResult(tb, startId + offset++);
    assertOptimizeHistory(optimizeHistory, OptimizeType.Major, 3, 0);
    assertIds(readRecords(table), 5, 9, 13, 2, 6, 10, 14);

    assertOptimizeHangUp(tb, startId + offset);
  }

  public void testNoPkPartitionTableOptimizing() {
    int offset = 1;
    UnkeyedTable table = arcticTable.asUnkeyedTable();
    TableIdentifier tb = table.id();

    // Step 1: insert data
    writeBase(table, Lists.newArrayList(
        newRecord(3, "aaa", quickDateWithZone(3)),
        newRecord(4, "bbb", quickDateWithZone(3)),
        newRecord(5, "eee", quickDateWithZone(4)),
        newRecord(6, "ddd", quickDateWithZone(4))
    ));

    // Step 2: insert data
    writeBase(table, Lists.newArrayList(
        newRecord(7, "fff", quickDateWithZone(3)),
        newRecord(8, "ggg", quickDateWithZone(3)),
        newRecord(9, "hhh", quickDateWithZone(4)),
        newRecord(10, "iii", quickDateWithZone(4))
    ));
    // wait Major Optimize result
    OptimizeHistory optimizeHistory = waitOptimizeResult(tb, startId + offset++);
    assertOptimizeHistory(optimizeHistory, OptimizeType.Major, 4, 2);
    assertIds(readRecords(table), 3, 4, 5, 6, 7, 8, 9, 10);

    // Step 3: insert data
    writeBase(table, Lists.newArrayList(
        newRecord(11, "jjj", quickDateWithZone(3)),
        newRecord(12, "kkk", quickDateWithZone(3)),
        newRecord(13, "lll", quickDateWithZone(4)),
        newRecord(14, "mmm", quickDateWithZone(4))
    ));
    // wait Major Optimize result
    optimizeHistory = waitOptimizeResult(tb, startId + offset++);
    assertOptimizeHistory(optimizeHistory, OptimizeType.Major, 4, 2);
    assertIds(readRecords(table), 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14);

    assertOptimizeHangUp(tb, startId + offset);
  }

  public void testNoPkTableOptimizing() {
    int offset = 1;
    UnkeyedTable table = arcticTable.asUnkeyedTable();
    TableIdentifier tb = table.id();

    // Step 1: insert data
    writeBase(table, Lists.newArrayList(
        newRecord(3, "aaa", quickDateWithZone(3)),
        newRecord(4, "bbb", quickDateWithZone(3)),
        newRecord(5, "eee", quickDateWithZone(4)),
        newRecord(6, "ddd", quickDateWithZone(4))
    ));

    // Step 2: insert data
    writeBase(table, Lists.newArrayList(
        newRecord(7, "fff", quickDateWithZone(3)),
        newRecord(8, "ggg", quickDateWithZone(3)),
        newRecord(9, "hhh", quickDateWithZone(4)),
        newRecord(10, "iii", quickDateWithZone(4))
    ));
    // wait Major Optimize result
    OptimizeHistory optimizeHistory = waitOptimizeResult(tb, startId + offset++);
    assertOptimizeHistory(optimizeHistory, OptimizeType.Major, 2, 1);
    assertIds(readRecords(table), 3, 4, 5, 6, 7, 8, 9, 10);

    // Step 3: insert data
    writeBase(table, Lists.newArrayList(
        newRecord(11, "jjj", quickDateWithZone(3)),
        newRecord(12, "kkk", quickDateWithZone(3)),
        newRecord(13, "lll", quickDateWithZone(4)),
        newRecord(14, "mmm", quickDateWithZone(4))
    ));
    // wait Major Optimize result
    optimizeHistory = waitOptimizeResult(tb, startId + offset++);
    assertOptimizeHistory(optimizeHistory, OptimizeType.Major, 2, 1);
    assertIds(readRecords(table), 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14);

    assertOptimizeHangUp(tb, startId + offset);
  }

  public void testKeyedTableTxIdNotInOrder() {
    int offset = 1;
    KeyedTable table = arcticTable.asKeyedTable();
    TableIdentifier tb = table.id();
    updateProperties(table, TableProperties.CHANGE_FILE_INDEX_HASH_BUCKET, "1");
    updateProperties(table, TableProperties.SELF_OPTIMIZING_MAX_FILE_CNT, "5");
    updateProperties(table, TableProperties.BASE_FILE_INDEX_HASH_BUCKET, "1");
    updateProperties(table, TableProperties.ENABLE_SELF_OPTIMIZING, "false");
    // Step1: add 1 change file
    writeChange(table, Lists.newArrayList(
        newRecord(1, "aaa", quickDateWithZone(3))
    ), null);

    long txId = table.beginTransaction(null);
    // Step2: add 1 change file
    writeChange(table, Lists.newArrayList(
        newRecord(2, "bbb", quickDateWithZone(3))
    ), null);

    // Step3: update change data, insert 2 file
    writeChange(table, Lists.newArrayList(
        newRecord(2, "bbb_new", quickDateWithZone(3))), Lists.newArrayList(
        newRecord(2, "bbb", quickDateWithZone(3))
    ));

    // Step4: update change data, insert 1 file
    writeChange(table, Lists.newArrayList(
        newRecord(3, "ccc", quickDateWithZone(3))
    ), null);

    // Step5: insert 1 change file with small txId, total 6 change files
    writeChangeWithTxId(table, Lists.newArrayList(
        newRecord(2, "bbb_old", quickDateWithZone(3))
    ), null, txId);

    assertIds(readRecords(table), 1, 2, 3);
    assertNames(readRecords(table), "aaa", "bbb_new", "ccc");

    updateProperties(table, TableProperties.ENABLE_SELF_OPTIMIZING, "true");

    // wait Optimize result
    OptimizeHistory optimizeHistory = waitOptimizeResult(tb, startId + offset++);
    assertOptimizeHistory(optimizeHistory, OptimizeType.Minor, 1, 1);
    optimizeHistory = waitOptimizeResult(tb, startId + offset++);
    assertOptimizeHistory(optimizeHistory, OptimizeType.Minor, 6, 5);
    optimizeHistory = waitOptimizeResult(tb, startId + offset++);
    assertOptimizeHistory(optimizeHistory, OptimizeType.Major, 6, 1);

    table.refresh();
    assertIds(readRecords(table), 1, 2, 3);
    assertNames(readRecords(table), "aaa", "bbb_new", "ccc");

    assertOptimizeHangUp(tb, startId + offset);
  }

  public void testPartitionArcticTablePartialOptimizing() {
    int offset = 1;
    KeyedTable table = arcticTable.asKeyedTable();
    TableIdentifier tb = table.id();
    emptyCommit(table);
    emptyCommit(table);
    emptyCommit(table);
    emptyCommit(table);

    // Step1: insert base data
    List<DataFile> dataFiles = writeBase(table, Lists.newArrayList(
        newRecord(1, "aaa", quickDateWithZone(3)),
        newRecord(2, "bbb", quickDateWithZone(4)),
        newRecord(3, "ccc", quickDateWithZone(5))
    ));

    long maxFileSizeLimit = dataFiles.get(0).fileSizeInBytes() * 4 - 100;
    updateProperties(table, TableProperties.SELF_OPTIMIZING_MAX_FILE_SIZE_BYTES, maxFileSizeLimit + "");

    // Step2: insert base data
    writeBase(table, Lists.newArrayList(
        newRecord(1, "aaa", quickDateWithZone(3)),
        newRecord(2, "bbb", quickDateWithZone(4)),
        newRecord(3, "ccc", quickDateWithZone(5))
    ));

    // wait Major Optimize result
    OptimizeHistory optimizeHistory = waitOptimizeResult(tb, startId + offset++);
    assertOptimizeHistory(optimizeHistory, OptimizeType.Major, 4, 2);
    optimizeHistory = waitOptimizeResult(tb, startId + offset++);
    assertOptimizeHistory(optimizeHistory, OptimizeType.Major, 2, 1);
    assertIds(readRecords(table), 1, 1, 2, 2, 3, 3);

    assertOptimizeHangUp(tb, startId + offset);
  }

  private Record newRecord(Object... val) {
    return newRecord(arcticTable.schema(), val);
  }

  public void emptyCommit(KeyedTable table) {
    AppendFiles appendFiles = table.changeTable().newAppend();
    appendFiles.commit();
  }
}
