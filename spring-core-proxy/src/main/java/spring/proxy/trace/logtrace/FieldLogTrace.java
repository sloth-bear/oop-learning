package spring.proxy.trace.logtrace;

import lombok.extern.slf4j.Slf4j;
import spring.proxy.trace.TraceId;
import spring.proxy.trace.TraceStatus;

@Slf4j
public class FieldLogTrace implements LogTrace {

  private static final String START_PREFIX = "-->";
  private static final String COMPLETE_PREFIX = "<--";
  private static final String EX_PREFIX = "<X-";

  private TraceId traceIdHolder; //동시성 이슈 발생

  @Override
  public TraceStatus begin(final String message) {
    syncTraceId();
    final TraceId traceId = traceIdHolder;
    final Long startTimeMs = System.currentTimeMillis();
    log.info("[{}] {}{}", traceId.getId(), addSpace(START_PREFIX, traceId.getLevel()), message);

    return new TraceStatus(traceId, startTimeMs, message);
  }

  @Override
  public void end(final TraceStatus status) {
    complete(status, null);
  }

  @Override
  public void exception(final TraceStatus status, final Exception e) {
    complete(status, e);
  }

  private void complete(final TraceStatus status, final Exception e) {
    final Long stopTimeMs = System.currentTimeMillis();
    final long resultTimeMs = stopTimeMs - status.getStartTimeMs();
    final TraceId traceId = status.getTraceId();
    if (e == null) {
      log.info("[{}] {}{} time={}ms", traceId.getId(),
          addSpace(COMPLETE_PREFIX, traceId.getLevel()), status.getMessage(), resultTimeMs);
    } else {
      log.info("[{}] {}{} time={}ms ex={}", traceId.getId(),
          addSpace(EX_PREFIX, traceId.getLevel()), status.getMessage(), resultTimeMs, e.toString());
    }

    releaseTraceId();
  }

  private void syncTraceId() {
    if (traceIdHolder == null) {
      traceIdHolder = new TraceId();
    } else {
      traceIdHolder = traceIdHolder.createNextId();
    }
  }

  private void releaseTraceId() {
    if (traceIdHolder.isFirstLevel()) {
      traceIdHolder = null; //destroy
    } else {
      traceIdHolder = traceIdHolder.createPreviousId();
    }
  }

  private static String addSpace(final String prefix, final int level) {
    final StringBuilder sb = new StringBuilder();
    for (int i = 0; i < level; i++) {
      sb.append((i == level - 1) ? "|" + prefix : "|   ");
    }
    return sb.toString();
  }
}
