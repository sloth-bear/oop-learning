package spring.advanced.trace.log.callback;

import lombok.RequiredArgsConstructor;
import spring.advanced.trace.TraceStatus;
import spring.advanced.trace.log.LogTrace;

@RequiredArgsConstructor
public class TraceTemplate {

  private final LogTrace trace;

  public <T> T execute(final String message, final TraceCallback<T> callback) {
    TraceStatus status = null;
    try {
      status = trace.begin(message);
      final var result = callback.call();
      trace.end(status);
      return result;
    } catch (final Exception e) {
      assert status != null;
      trace.exception(status, e);
      throw e;
    }
  }

}
