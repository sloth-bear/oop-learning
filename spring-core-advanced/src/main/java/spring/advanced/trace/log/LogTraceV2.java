package spring.advanced.trace.log;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import spring.advanced.trace.TraceId;
import spring.advanced.trace.TraceStatus;

@Slf4j
@Component
public class LogTraceV2 {

  private static final String START_PREFIX = "-->";
  private static final String COMPLETE_PREFIX = "<--";
  private static final String EX_PREFIX = "<X-";

  public TraceStatus begin(final String message) {
    final var traceId = new TraceId();
    final var startTimeMs = System.currentTimeMillis();

    log.info("[{}] {}{}", traceId.getId(), addSpace(START_PREFIX, traceId.getLevel()), message);

    return new TraceStatus(traceId, startTimeMs, message);
  }

  public TraceStatus begin(final TraceId prev, final String message) {
    final var nextTrace = prev.createNextLevel();
    final var startTimeMs = System.currentTimeMillis();

    log.info("[{}] {}{}", nextTrace.getId(), addSpace(START_PREFIX, nextTrace.getLevel()), message);

    return new TraceStatus(nextTrace, startTimeMs, message);
  }

  public void end(final TraceStatus traceStatus) {
    final var traceId = traceStatus.getTraceId();
    log.info(
        "[{}] {}{} time={}ms",
        traceId.getId(),
        addSpace(COMPLETE_PREFIX, traceId.getLevel()),
        traceStatus.getMessage(),
        getTimeDiff(traceStatus.getStartTimeMs())
    );
  }

  public void exception(final TraceStatus traceStatus, final Exception e) {
    final var traceId = traceStatus.getTraceId();

    log.info(
        "[{}] {}{} time={}ms, ex={}",
        traceId.getId(),
        addSpace(EX_PREFIX, traceId.getLevel()),
        traceStatus.getMessage(),
        getTimeDiff(traceStatus.getStartTimeMs()),
        e.toString()
    );
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

}
