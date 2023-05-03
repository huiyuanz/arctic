package com.netease.arctic.ams.server.optimizing.plan;

import com.google.common.collect.Maps;
import com.netease.arctic.ams.server.optimizing.OptimizingType;
import com.netease.arctic.ams.server.table.TableRuntime;
import com.netease.arctic.table.ArcticTable;
import com.netease.arctic.utils.TableTypeUtil;
import org.apache.iceberg.DataFile;
import org.apache.iceberg.DeleteFile;
import org.apache.iceberg.FileContent;
import org.apache.iceberg.FileScanTask;
import org.apache.iceberg.PartitionSpec;
import org.apache.iceberg.StructLike;
import org.apache.iceberg.io.CloseableIterable;
import org.apache.iceberg.relocated.com.google.common.collect.Lists;
import org.glassfish.jersey.internal.guava.Sets;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class OptimizingEvaluator {

  protected final ArcticTable arcticTable;
  protected final TableRuntime tableRuntime;
  protected boolean isInitEvaluator = false;

  protected Map<String, AbstractPartitionPlan> partitionEvaluatorMap = Maps.newHashMap();

  public OptimizingEvaluator(TableRuntime tableRuntime) {
    this(tableRuntime, tableRuntime.loadTable());
  }

  public OptimizingEvaluator(TableRuntime tableRuntime, ArcticTable table) {
    this.tableRuntime = tableRuntime;
    this.arcticTable = table;
  }

  protected void initEvaluator() {
    if (TableTypeUtil.isIcebergTableFormat(arcticTable)) {
      initPartitionPlans();
    } else {
      throw new UnsupportedOperationException();
    }
    isInitEvaluator = true;
  }

  private void initPartitionPlans() {
    long targetFileSize = tableRuntime.getTargetSize();
    long maxFragmentSize = targetFileSize / tableRuntime.getOptimizingConfig().getFragmentRatio();
    List<FileScanTask> fileScanTasks;
    try (CloseableIterable<FileScanTask> filesIterable =
        arcticTable.asUnkeyedTable().newScan().planFiles()) {
      fileScanTasks = Lists.newArrayList(filesIterable);
    } catch (IOException e) {
      throw new UncheckedIOException("Failed to close table scan of " + arcticTable.id(), e);
    }
    PartitionSpec partitionSpec = arcticTable.asUnkeyedTable().spec();
    for (FileScanTask fileScanTask : fileScanTasks) {
      StructLike partition = fileScanTask.file().partition();
      String partitionPath = partitionSpec.partitionToPath(partition);
      if (fiterPartition(partitionPath)) {
        continue;
      }
      AbstractPartitionPlan evaluator = partitionEvaluatorMap.computeIfAbsent(partitionPath, this::buildEvaluator);
      evaluator.addFile(fileScanTask.file(), fileScanTask.deletes());
    }
    partitionEvaluatorMap.values().removeIf(plan -> !plan.isNecessary());
  }

  protected AbstractPartitionPlan buildEvaluator(String partitionPath) {
    return new PartitionEvaluatorImpl(tableRuntime, partitionPath);
  }

  protected boolean fiterPartition(String partition) {
    return false;
  }

  public boolean isNecessary() {
    if (!isInitEvaluator) {
      initEvaluator();
    }
    return !partitionEvaluatorMap.isEmpty();
  }

  public PendingInput getPendingInput() {
    if (!isInitEvaluator) {
      initEvaluator();
    }
    return new PendingInput(partitionEvaluatorMap.values());
  }

  public static class PendingInput {

    private final Set<String> partitions = Sets.newHashSet();

    private int dataFileCount = 0;
    private long dataFileSize = 0;
    private int equalityDeleteFileCount = 0;
    private int positionalDeleteFileCount = 0;
    private long positionalDeleteBytes = 0L;
    private long equalityDeleteBytes = 0L;

    public PendingInput(Collection<AbstractPartitionPlan> evaluators) {
      for (AbstractPartitionPlan e : evaluators) {
        PartitionEvaluatorImpl evaluator = (PartitionEvaluatorImpl) e;
        partitions.add(evaluator.getPartition());
        dataFileCount += evaluator.fragementFileCount + evaluator.segmentFileCount;
        dataFileSize += evaluator.fragementFileSize + evaluator.segmentFileSize;
        positionalDeleteBytes += evaluator.positionalDeleteBytes;
        positionalDeleteFileCount += evaluator.positionalDeleteFileCount;
        equalityDeleteBytes += evaluator.equalityDeleteBytes;
        equalityDeleteFileCount += evaluator.equalityDeleteFileCount;
      }
    }

    public Set<String> getPartitions() {
      return partitions;
    }

    public int getDataFileCount() {
      return dataFileCount;
    }

    public long getDataFileSize() {
      return dataFileSize;
    }

    public int getEqualityDeleteFileCount() {
      return equalityDeleteFileCount;
    }

    public int getPositionalDeleteFileCount() {
      return positionalDeleteFileCount;
    }

    public long getPositionalDeleteBytes() {
      return positionalDeleteBytes;
    }

    public long getEqualityDeleteBytes() {
      return equalityDeleteBytes;
    }
  }

  private class PartitionEvaluatorImpl extends AbstractPartitionPlan {

    private int fragementFileCount = 0;
    private long fragementFileSize = 0;
    private int segmentFileCount = 0;
    private long segmentFileSize = 0;
    private int equalityDeleteFileCount = 0;
    private int positionalDeleteFileCount = 0;
    private long positionalDeleteBytes = 0L;
    private long equalityDeleteBytes = 0L;
    private long rewriteSegmentFileSize = 0L;

    public PartitionEvaluatorImpl(TableRuntime tableRuntime, String partition) {
      super(tableRuntime, arcticTable, partition);
    }

    @Override
    public void addFile(DataFile dataFile, List<DeleteFile> deletes) {
      boolean isSegment = false;
      int posDeleteCount = 0;
      if (dataFile.fileSizeInBytes() <= fragementSize) {
        fragementFileSize += dataFile.fileSizeInBytes();
        fragementFileCount += 1;
      } else {
        segmentFileSize += dataFile.fileSizeInBytes();
        segmentFileCount += 1;
        isSegment = true;
      }
      for (DeleteFile delete : deletes) {
        if (delete.content() == FileContent.EQUALITY_DELETES) {
          equalityDeleteFileCount += 1;
          equalityDeleteBytes += delete.fileSizeInBytes();
        } else {
          if (++posDeleteCount > 1 || isSegment &&
              delete.recordCount() >= dataFile.recordCount() * config.getMajorDuplicateRatio()) {
            rewriteSegmentFileSize += dataFile.fileSizeInBytes();
          }
          posDeleteCount += 1;
          positionalDeleteBytes += delete.fileSizeInBytes();
        }
      }
    }

    @Override
    public boolean isNecessary() {
      return isMajorNecessary() || isMinorNecessary();
    }

    @Override
    public long getCost() {
      throw new UnsupportedOperationException();
    }

    @Override
    public OptimizingType getOptimizingType() {
      return isMajorNecessary() ? OptimizingType.MAJOR : OptimizingType.MINOR;
    }

    private boolean isMajorNecessary() {
      return rewriteSegmentFileSize > 0;
    }

    private boolean isMinorNecessary() {
      int sourceFileCount = fragementFileCount + equalityDeleteFileCount;
      return sourceFileCount >= config.getMinorLeastFileCount() ||
          (sourceFileCount > 1 &&
              System.currentTimeMillis() - tableRuntime.getLastMinorOptimizingTime() > config.getMinorLeastInterval());
    }
  }
}
