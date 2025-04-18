/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License,
      Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
      software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
      either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.netease.arctic.flink.read.source;

import org.apache.flink.configuration.Configuration;
import org.apache.flink.util.Preconditions;
import org.apache.iceberg.Schema;
import org.apache.iceberg.expressions.Expression;
import org.apache.iceberg.flink.FlinkConfigOptions;
import org.apache.iceberg.flink.source.ScanContext;
import org.apache.iceberg.flink.source.StreamingStartingStrategy;

import java.io.Serializable;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.netease.arctic.flink.table.descriptors.ArcticValidator.ARCTIC_READ_FILE;
import static com.netease.arctic.flink.table.descriptors.ArcticValidator.ARCTIC_READ_MODE;
import static com.netease.arctic.flink.table.descriptors.ArcticValidator.SCAN_STARTUP_MODE;
import static com.netease.arctic.flink.table.descriptors.ArcticValidator.SCAN_STARTUP_MODE_EARLIEST;
import static com.netease.arctic.flink.table.descriptors.ArcticValidator.SCAN_STARTUP_MODE_LATEST;
import static org.apache.iceberg.TableProperties.DEFAULT_NAME_MAPPING;

/**
 * This is an arctic source scan context.
 */
public class ArcticScanContext extends ScanContext implements Serializable {

  private static final long serialVersionUID = 1L;

  private final String scanStartupMode;

  protected ArcticScanContext(
      boolean caseSensitive,
      Long snapshotId,
      StreamingStartingStrategy startingStrategy,
      Long startSnapshotTimestamp,
      Long startSnapshotId,
      Long endSnapshotId,
      Long asOfTimestamp,
      Long splitSize,
      Integer splitLookback,
      Long splitOpenFileCost,
      boolean isStreaming,
      Duration monitorInterval,
      String nameMapping,
      Schema schema,
      List<Expression> filters,
      long limit,
      boolean includeColumnStats,
      boolean exposeLocality,
      Integer planParallelism,
      int maxPlanningSnapshotCount,
      String scanStartupMode) {
    super(caseSensitive,
        snapshotId,
        startingStrategy,
        startSnapshotTimestamp,
        startSnapshotId,
        endSnapshotId,
        asOfTimestamp,
        splitSize,
        splitLookback,
        splitOpenFileCost,
        isStreaming,
        monitorInterval,
        nameMapping,
        schema,
        filters,
        limit,
        includeColumnStats,
        exposeLocality,
        planParallelism,
        maxPlanningSnapshotCount);
    this.scanStartupMode = scanStartupMode;
  }

  public boolean caseSensitive() {
    return caseSensitive;
  }

  public Long snapshotId() {
    return snapshotId;
  }

  public Long startSnapshotId() {
    return startSnapshotId;
  }

  public Long endSnapshotId() {
    return endSnapshotId;
  }

  public Long asOfTimestamp() {
    return asOfTimestamp;
  }

  public Long splitSize() {
    return splitSize;
  }

  public Integer splitLookback() {
    return splitLookback;
  }

  public Long splitOpenFileCost() {
    return splitOpenFileCost;
  }

  public boolean isStreaming() {
    return isStreaming;
  }

  public Duration monitorInterval() {
    return monitorInterval;
  }

  public String nameMapping() {
    return nameMapping;
  }

  public Schema project() {
    return schema;
  }

  public List<Expression> filters() {
    return filters;
  }

  public long limit() {
    return limit;
  }

  public static Builder arcticBuilder() {
    return new Builder();
  }

  public String scanStartupMode() {
    return scanStartupMode;
  }

  public static class Builder {
    private boolean caseSensitive = CASE_SENSITIVE.defaultValue();
    private Long snapshotId = SNAPSHOT_ID.defaultValue();
    private StreamingStartingStrategy startingStrategy = STARTING_STRATEGY.defaultValue();
    private Long startSnapshotTimestamp = START_SNAPSHOT_TIMESTAMP.defaultValue();
    private Long startSnapshotId = START_SNAPSHOT_ID.defaultValue();
    private Long endSnapshotId = END_SNAPSHOT_ID.defaultValue();
    private Long asOfTimestamp = AS_OF_TIMESTAMP.defaultValue();
    private Long splitSize = SPLIT_SIZE.defaultValue();
    private Integer splitLookback = SPLIT_LOOKBACK.defaultValue();
    private Long splitOpenFileCost = SPLIT_FILE_OPEN_COST.defaultValue();
    private boolean isStreaming = STREAMING.defaultValue();
    private Duration monitorInterval = MONITOR_INTERVAL.defaultValue();
    private String nameMapping;
    private Schema projectedSchema;
    private List<Expression> filters;
    private long limit = -1L;
    private boolean exposeLocality;
    private Integer planParallelism =
        FlinkConfigOptions.TABLE_EXEC_ICEBERG_WORKER_POOL_SIZE.defaultValue();
    private int maxPlanningSnapshotCount = MAX_PLANNING_SNAPSHOT_COUNT.defaultValue();
    private String scanStartupMode;
    private boolean includeColumnStats = INCLUDE_COLUMN_STATS.defaultValue();

    private Builder() {
    }

    public Builder caseSensitive(boolean newCaseSensitive) {
      this.caseSensitive = newCaseSensitive;
      return this;
    }

    public Builder useSnapshotId(Long newSnapshotId) {
      this.snapshotId = newSnapshotId;
      return this;
    }

    public Builder startingStrategy(StreamingStartingStrategy newStartingStrategy) {
      this.startingStrategy = newStartingStrategy;
      return this;
    }

    public Builder startSnapshotTimestamp(Long newStartSnapshotTimestamp) {
      this.startSnapshotTimestamp = newStartSnapshotTimestamp;
      return this;
    }

    public Builder startSnapshotId(Long newStartSnapshotId) {
      this.startSnapshotId = newStartSnapshotId;
      return this;
    }

    public Builder endSnapshotId(Long newEndSnapshotId) {
      this.endSnapshotId = newEndSnapshotId;
      return this;
    }

    public Builder asOfTimestamp(Long newAsOfTimestamp) {
      this.asOfTimestamp = newAsOfTimestamp;
      return this;
    }

    public Builder splitSize(Long newSplitSize) {
      this.splitSize = newSplitSize;
      return this;
    }

    public Builder splitLookback(Integer newSplitLookback) {
      this.splitLookback = newSplitLookback;
      return this;
    }

    public Builder splitOpenFileCost(Long newSplitOpenFileCost) {
      this.splitOpenFileCost = newSplitOpenFileCost;
      return this;
    }

    public Builder streaming(boolean streaming) {
      this.isStreaming = streaming;
      return this;
    }

    public Builder monitorInterval(Duration newMonitorInterval) {
      this.monitorInterval = newMonitorInterval;
      return this;
    }

    public Builder nameMapping(String newNameMapping) {
      this.nameMapping = newNameMapping;
      return this;
    }

    public Builder project(Schema newProjectedSchema) {
      this.projectedSchema = newProjectedSchema;
      return this;
    }

    public Builder filters(List<Expression> newFilters) {
      this.filters = newFilters;
      return this;
    }

    public Builder limit(long newLimit) {
      this.limit = newLimit;
      return this;
    }

    public Builder exposeLocality(boolean newExposeLocality) {
      this.exposeLocality = newExposeLocality;
      return this;
    }

    public Builder planParallelism(Integer parallelism) {
      this.planParallelism = parallelism;
      return this;
    }

    public Builder maxPlanningSnapshotCount(int newMaxPlanningSnapshotCount) {
      this.maxPlanningSnapshotCount = newMaxPlanningSnapshotCount;
      return this;
    }

    public Builder scanStartupMode(String scanStartupMode) {
      this.scanStartupMode = scanStartupMode;
      return this;
    }

    public Builder includeColumnStats(boolean newIncludeColumnStats) {
      this.includeColumnStats = newIncludeColumnStats;
      return this;
    }

    public Builder fromProperties(Map<String, String> properties) {
      Configuration config = new Configuration();
      properties.forEach(config::setString);

      return this.useSnapshotId(config.get(SNAPSHOT_ID))
          .caseSensitive(config.get(CASE_SENSITIVE))
          .asOfTimestamp(config.get(AS_OF_TIMESTAMP))
          .startingStrategy(config.get(STARTING_STRATEGY))
          .startSnapshotTimestamp(config.get(START_SNAPSHOT_TIMESTAMP))
          .startSnapshotId(config.get(START_SNAPSHOT_ID))
          .endSnapshotId(config.get(END_SNAPSHOT_ID))
          .splitSize(config.get(SPLIT_SIZE))
          .splitLookback(config.get(SPLIT_LOOKBACK))
          .splitOpenFileCost(config.get(SPLIT_FILE_OPEN_COST))
          .streaming(config.get(STREAMING))
          .monitorInterval(config.get(MONITOR_INTERVAL))
          .nameMapping(properties.get(DEFAULT_NAME_MAPPING))
          .scanStartupMode(properties.get(SCAN_STARTUP_MODE.key()))
          .includeColumnStats(config.get(INCLUDE_COLUMN_STATS))
          .maxPlanningSnapshotCount(config.get(MAX_PLANNING_SNAPSHOT_COUNT));
    }

    public ArcticScanContext build() {
      scanStartupMode = scanStartupMode == null ? null : scanStartupMode.toLowerCase();
      Preconditions.checkArgument(Objects.isNull(scanStartupMode) ||
              Objects.equals(scanStartupMode, SCAN_STARTUP_MODE_EARLIEST) ||
              Objects.equals(scanStartupMode, SCAN_STARTUP_MODE_LATEST),
          String.format("only support %s, %s when %s is %s",
              SCAN_STARTUP_MODE_EARLIEST, SCAN_STARTUP_MODE_LATEST, ARCTIC_READ_MODE, ARCTIC_READ_FILE));
      return new ArcticScanContext(
          caseSensitive,
          snapshotId,
          startingStrategy,
          startSnapshotTimestamp,
          startSnapshotId,
          endSnapshotId,
          asOfTimestamp,
          splitSize,
          splitLookback,
          splitOpenFileCost,
          isStreaming,
          monitorInterval,
          nameMapping,
          projectedSchema,
          filters,
          limit,
          includeColumnStats,
          exposeLocality,
          planParallelism,
          maxPlanningSnapshotCount,
          scanStartupMode);
    }
  }
}
