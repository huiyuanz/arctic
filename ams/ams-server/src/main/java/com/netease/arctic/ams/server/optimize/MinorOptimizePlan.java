/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.netease.arctic.ams.server.optimize;

import com.netease.arctic.ams.api.OptimizeType;
import com.netease.arctic.ams.server.model.BasicOptimizeTask;
import com.netease.arctic.ams.server.model.FileTree;
import com.netease.arctic.ams.server.model.TableOptimizeRuntime;
import com.netease.arctic.ams.server.model.TaskConfig;
import com.netease.arctic.data.DataTreeNode;
import com.netease.arctic.data.file.ContentFileWithSequence;
import com.netease.arctic.table.ArcticTable;
import com.netease.arctic.table.TableProperties;
import com.netease.arctic.utils.CompatiblePropertyUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.iceberg.DataFile;
import org.apache.iceberg.DeleteFile;
import org.apache.iceberg.FileScanTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class MinorOptimizePlan extends AbstractArcticOptimizePlan {
  private static final Logger LOG = LoggerFactory.getLogger(MinorOptimizePlan.class);
  // cache partition change file count
  private final Map<String, Integer> partitionChangeFileCount = new HashMap<>();

  public MinorOptimizePlan(ArcticTable arcticTable, TableOptimizeRuntime tableOptimizeRuntime,
                           List<FileScanTask> baseFileScanTasks,
                           List<ContentFileWithSequence<?>> changeFileScanTasks,
                           int queueId, long currentTime, long changeSnapshotId, long baseSnapshotId) {
    super(arcticTable, tableOptimizeRuntime, changeFileScanTasks, baseFileScanTasks,
        queueId, currentTime, changeSnapshotId, baseSnapshotId);
  }

  @Override
  public boolean partitionNeedPlan(String partitionToPath) {
    int changeFileCount = getChangeFileCount(partitionToPath);

    // file count
    if (changeFileCount >= CompatiblePropertyUtil.propertyAsInt(arcticTable.properties(),
        TableProperties.SELF_OPTIMIZING_MINOR_TRIGGER_FILE_CNT,
        TableProperties.SELF_OPTIMIZING_MINOR_TRIGGER_FILE_CNT_DEFAULT)) {
      return true;
    }

    // optimize interval
    if (checkOptimizeInterval(partitionToPath)) {
      return true;
    }
    LOG.debug("{} ==== don't need {} optimize plan, skip partition {}", tableId(), getOptimizeType(), partitionToPath);
    return false;
  }

  private int getChangeFileCount(String partition) {
    Integer cached = partitionChangeFileCount.get(partition);
    if (cached != null) {
      return cached;
    }
    int changeFileCount = getInsertFilesFromFileTree(partition).size() + getDeleteFilesFromFileTree(partition).size();
    partitionChangeFileCount.put(partition, changeFileCount);
    return changeFileCount;
  }

  @Override
  protected long getTaskFileSize(BasicOptimizeTask task) {
    return task.getDeleteFileSize() + task.getInsertFileSize();
  }

  @Override
  protected long getLatestOptimizeTime(String partition) {
    return tableOptimizeRuntime.getLatestMinorOptimizeTime(partition);
  }

  @Override
  protected long getMaxOptimizeInterval() {
    return CompatiblePropertyUtil.propertyAsLong(arcticTable.properties(),
        TableProperties.SELF_OPTIMIZING_MINOR_TRIGGER_INTERVAL,
        TableProperties.SELF_OPTIMIZING_MINOR_TRIGGER_INTERVAL_DEFAULT);
  }

  @Override
  protected PartitionWeight getPartitionWeight(String partition) {
    return new MinorPartitionWeight(checkOptimizeInterval(partition), getChangeFileCount(partition));
  }

  protected static class MinorPartitionWeight implements PartitionWeight {
    private final boolean reachInterval;

    private final int changeFileCount;

    public MinorPartitionWeight(boolean reachInterval, int changeFileCount) {
      this.reachInterval = reachInterval;
      this.changeFileCount = changeFileCount;
    }

    @Override
    public int compareTo(PartitionWeight o) {
      MinorPartitionWeight that = (MinorPartitionWeight) o;
      int compare = Boolean.compare(that.reachInterval, this.reachInterval);
      if (compare != 0) {
        return compare;
      }
      return Integer.compare(that.changeFileCount, this.changeFileCount);
    }
  }

  @Override
  protected OptimizeType getOptimizeType() {
    return OptimizeType.Minor;
  }

  @Override
  protected List<BasicOptimizeTask> collectTask(String partition) {
    List<BasicOptimizeTask> result;

    result = collectKeyedTableTasks(partition);

    return result;
  }

  @Override
  protected boolean baseFileShouldOptimize(DataFile baseFile, String partition) {
    return true;
  }

  private List<BasicOptimizeTask> collectKeyedTableTasks(String partition) {
    FileTree treeRoot = partitionFileTree.get(partition);
    if (treeRoot == null) {
      return Collections.emptyList();
    }
    List<BasicOptimizeTask> collector = new ArrayList<>();
    String commitGroup = UUID.randomUUID().toString();
    long createTime = System.currentTimeMillis();

    TaskConfig taskPartitionConfig = new TaskConfig(OptimizeType.Minor, partition, commitGroup, planGroup, createTime,
        changeStoreToSequence, changeStoreFromSequence.get(partition));
    List<FileTree> subTrees = new ArrayList<>();
    // split tasks
    treeRoot.splitFileTree(subTrees, new SplitIfNoFileExists());
    for (FileTree subTree : subTrees) {
      List<DataFile> insertFiles = new ArrayList<>();
      List<DataFile> deleteFiles = new ArrayList<>();
      List<DataFile> baseFiles = new ArrayList<>();
      List<DeleteFile> posDeleteFiles = new ArrayList<>();
      subTree.collectInsertFiles(insertFiles);
      subTree.collectDeleteFiles(deleteFiles);
      subTree.collectBaseFiles(baseFiles);
      subTree.collectPosDeleteFiles(posDeleteFiles);
      // if no insert files and no eq-delete file, skip
      if (CollectionUtils.isEmpty(insertFiles) && CollectionUtils.isEmpty(deleteFiles)) {
        continue;
      }
      List<DataTreeNode> sourceNodes = new ArrayList<>();
      if (CollectionUtils.isNotEmpty(baseFiles)) {
        sourceNodes = Collections.singletonList(subTree.getNode());
      }
      collector.add(buildOptimizeTask(sourceNodes,
          insertFiles, deleteFiles, baseFiles, posDeleteFiles, taskPartitionConfig));
    }

    return collector;
  }

}
