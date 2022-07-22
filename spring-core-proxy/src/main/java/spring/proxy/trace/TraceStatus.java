package spring.proxy.trace;

public class TraceStatus {

  private final TraceId traceId;
  private final Long startTimeMs;
  private final String message;

  public TraceStatus(final TraceId traceId, final Long startTimeMs, final String message) {
    this.traceId = traceId;
    this.startTimeMs = startTimeMs;
    this.message = message;
  }

  public Long getStartTimeMs() {
    return startTimeMs;
  }

  public String getMessage() {
    return message;
  }

  public TraceId getTraceId() {
    return traceId;
  }
}
