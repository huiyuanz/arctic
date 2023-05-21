package com.netease.arctic.server.optimizing;

import com.netease.arctic.server.dashboard.model.TableOptimizingProcess;
import com.netease.arctic.server.persistence.PersistentBase;
import com.netease.arctic.server.persistence.mapper.OptimizingMapper;
import com.netease.arctic.table.TableIdentifier;
import org.apache.iceberg.data.Record;
import org.apache.iceberg.relocated.com.google.common.collect.Sets;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.TimeoutException;
import java.util.function.Supplier;

public class BaseOptimizingChecker extends PersistentBase {

  private static final Logger LOG = LoggerFactory.getLogger(BaseOptimizingChecker.class);
  private static final long WAIT_SUCCESS_TIMEOUT = 30_000;
  private static final long CHECK_TIMEOUT = 1_000;
  private final TableIdentifier tableIdentifier;
  private long lastProcessId;

  public BaseOptimizingChecker(TableIdentifier tableIdentifier) {
    this.tableIdentifier = tableIdentifier;
    this.lastProcessId = System.currentTimeMillis();
  }

  protected void assertIds(List<Record> actualRows, Object... expectIds) {
    assertRecordValues(actualRows, 0, expectIds);
  }

  protected void assertIdRange(List<Record> actualRows, int from, int to) {
    assertRecordValues(actualRows, 0, range(from, to).toArray());
  }

  protected List<Integer> range(int from, int to) {
    List<Integer> ids = new ArrayList<>();
    for (int i = from; i <= to; i++) {
      ids.add(i);
    }
    return ids;
  }

  protected void assertNames(List<Record> actualRows, Object... expectNames) {
    assertRecordValues(actualRows, 1, expectNames);
  }

  protected void assertRecordValues(List<Record> actualRows, int index, Object... expectValues) {
    Set<Object> actualValueSet = Sets.newHashSet();
    int cnt = 0;
    for (Record r : actualRows) {
      actualValueSet.add(r.get(index));
      cnt++;
    }
    Assert.assertEquals(cnt, expectValues.length);
    for (Object id : expectValues) {
      if (!actualValueSet.contains(id)) {
        throw new AssertionError("assert id contain " + id + ", but not found");
      }
    }
  }

  protected void assertOptimizingProcess(
      TableOptimizingProcess optimizingProcess,
      OptimizingType optimizeType,
      int fileCntBefore,
      int fileCntAfter) {
    Assert.assertNotNull(optimizingProcess);
    Assert.assertEquals(optimizeType, optimizingProcess.getOptimizingType());
    Assert.assertEquals(fileCntBefore, optimizingProcess.getSummary().getRewriteDataFileCnt());
    Assert.assertEquals(fileCntAfter, optimizingProcess.getSummary().getNewFileCnt());
  }

  protected TableOptimizingProcess waitOptimizeResult() {
    boolean success;
    try {
      success = waitUntilFinish(() -> {
        List<TableOptimizingProcess> tableOptimizingProcesses = getAs(
            OptimizingMapper.class,
            mapper -> mapper.selectSuccessOptimizingProcesses(tableIdentifier.getCatalog(),
                tableIdentifier.getDatabase(), tableIdentifier.getTableName()));
        if (tableOptimizingProcesses == null || tableOptimizingProcesses.isEmpty()) {
          LOG.info("optimize history is empty");
          return Status.RUNNING;
        }
        Optional<TableOptimizingProcess> any =
            tableOptimizingProcesses.stream().filter(p -> p.getProcessId() > lastProcessId).findAny();

        if (any.isPresent()) {
          return Status.SUCCESS;
        } else {
          LOG.info(
              "optimize max process id {}",
              tableOptimizingProcesses.stream()
                  .map(TableOptimizingProcess::getProcessId)
                  .max(Comparator.naturalOrder())
                  .get());
          return Status.RUNNING;
        }
      }, WAIT_SUCCESS_TIMEOUT);
    } catch (TimeoutException e) {
      throw new IllegalStateException("wait optimize result timeout expectRecordId " + lastProcessId, e);
    }

    if (success) {
      Optional<TableOptimizingProcess> result = getAs(
          OptimizingMapper.class,
          mapper -> mapper.selectSuccessOptimizingProcesses(tableIdentifier.getCatalog(),
              tableIdentifier.getDatabase(), tableIdentifier.getTableName())).stream()
          .filter(p -> p.getProcessId() > lastProcessId)
          .findAny();
      if (result.isPresent()) {
        this.lastProcessId = result.get().getProcessId();
        return result.get();
      } else {
        return null;
      }
    } else {
      return null;
    }
  }

  protected void assertOptimizeHangUp(TableIdentifier tableIdentifier, long notExpectProcessId) {
    try {
      Thread.sleep(CHECK_TIMEOUT);
    } catch (InterruptedException e) {
      throw new IllegalStateException("waiting result was interrupted");
    }
    List<TableOptimizingProcess> tableOptimizingProcesses = getAs(
        OptimizingMapper.class,
        mapper -> mapper.selectSuccessOptimizingProcesses(tableIdentifier.getCatalog(),
            tableIdentifier.getDatabase(), tableIdentifier.getTableName()));
    if (tableOptimizingProcesses == null || tableOptimizingProcesses.isEmpty()) {
      return;
    }
    Optional<TableOptimizingProcess> any =
        tableOptimizingProcesses.stream().filter(p -> p.getProcessId() >= notExpectProcessId).findAny();
    any.ifPresent(h -> LOG.error("{} get unexpected optimize process {} {}", tableIdentifier, notExpectProcessId, h));
    Assert.assertFalse("optimize is not stopped", any.isPresent());
  }

  protected boolean waitUntilFinish(Supplier<Status> statusSupplier, final long timeout)
      throws TimeoutException {
    long start = System.currentTimeMillis();
    while (true) {
      long duration = System.currentTimeMillis() - start;
      if (duration > timeout) {
        throw new TimeoutException("wait exceed timeout, " + duration + "ms > " + timeout + "ms");
      }
      Status status = statusSupplier.get();
      if (status == Status.FAILED) {
        return false;
      } else if (status == Status.RUNNING) {
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          throw new IllegalStateException("waiting result was interrupted");
        }
      } else {
        return true;
      }
    }
  }

  private enum Status {
    SUCCESS,
    FAILED,
    RUNNING
  }
}