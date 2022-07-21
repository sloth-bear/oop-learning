package spring.advanced.trace.log.templtae;

import spring.advanced.trace.TraceStatus;
import spring.advanced.trace.log.LogTrace;

public abstract class AbstractTemplate<T> {

  private final LogTrace trace;

  public AbstractTemplate(final LogTrace trace) {
    this.trace = trace;
  }

  public T execute(final String message) {
    TraceStatus status = null;
    try {
      status = trace.begin(message);
      final var result = doProcess();
      trace.end(status);
      return result;
    } catch (final Exception e) {
      assert status != null;
      trace.exception(status, e);
      throw e;
    }
  }

  protected abstract T doProcess();

}
