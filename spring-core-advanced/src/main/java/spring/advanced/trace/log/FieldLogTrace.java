package spring.advanced.trace.log;

import lombok.extern.slf4j.Slf4j;
import spring.advanced.trace.TraceId;
import spring.advanced.trace.TraceStatus;

@Slf4j
public class FieldLogTrace implements LogTrace {

  private static final String START_PREFIX = "-->";
  private static final String COMPLETE_PREFIX = "<--";
  private static final String EX_PREFIX = "<X-";

  // TODO Fix concurrency issue
  private TraceId traceIdHolder;

  @Override
  public TraceStatus begin(final String message) {
    syncTraceId();

    final var traceId = traceIdHolder;
    final var startTimeMs = System.currentTimeMillis();

    log.info("[{}] {}{}", traceId.getId(), addSpace(START_PREFIX, traceId.getLevel()), message);

    return new TraceStatus(traceId, startTimeMs, message);
  }

  @Override
  public void end(final TraceStatus status) {
    final var traceId = status.getTraceId();
    log.info(
        "[{}] {}{} time={}ms",
        traceId.getId(),
        addSpace(COMPLETE_PREFIX, traceId.getLevel()),
        status.getMessage(),
        getTimeDiff(status.getStartTimeMs())
    );

    releaseTraceId();
  }

  @Override
  public void exception(final TraceStatus status, final Exception e) {
    final var traceId = status.getTraceId();

    log.info(
        "[{}] {}{} time={}ms, ex={}",
        traceId.getId(),
        addSpace(EX_PREFIX, traceId.getLevel()),
        status.getMessage(),
        getTimeDiff(status.getStartTimeMs()),
        e.toString()
    );

    releaseTraceId();
  }


  private long getTimeDiff(final long startTimeMs) {
    return System.currentTimeMillis() - startTimeMs;
  }

  private static String addSpace(final String prefix, final int level) {
    final var sb = new StringBuilder();
    for (int i = 0; i < level; i++) {
      sb.append((i == level - 1) ? "|" + prefix : "|     ");
    }
    return sb.toString();
  }

  private void syncTraceId() {
    if (traceIdHolder == null) {
      traceIdHolder = new TraceId();
    } else {
      traceIdHolder = traceIdHolder.createNextLevel();
    }
  }

  private void releaseTraceId() {
    if (traceIdHolder.isFirstLevel()) {
      destroyTraceId();
    } else {
      traceIdHolder = traceIdHolder.createPrevLevel();
    }
  }

  private void destroyTraceId() {
    traceIdHolder = null;
  }

}
