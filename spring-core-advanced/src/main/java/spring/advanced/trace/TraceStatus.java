package spring.advanced.trace;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * This object represents trace status of log including the transaction id, startTime of log... some
 * info for log trace.
 */
@Getter
@RequiredArgsConstructor
public class TraceStatus {

  private final TraceId traceId;
  private final Long startTimeMs;
  private final String message;

}
