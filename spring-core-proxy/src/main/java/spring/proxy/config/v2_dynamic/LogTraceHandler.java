package spring.proxy.config.v2_dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import spring.proxy.trace.TraceStatus;
import spring.proxy.trace.logtrace.LogTrace;

public class LogTraceHandler implements InvocationHandler {

  private final Object target;
  private final LogTrace logTrace;

  public LogTraceHandler(final Object target, final LogTrace logTrace) {
    this.target = target;
    this.logTrace = logTrace;
  }

  @Override
  public Object invoke(final Object proxy, final Method method, final Object[] args)
      throws Throwable {
    TraceStatus begin = null;
    try {
      begin = logTrace.begin(
          method.getDeclaringClass().getSimpleName() + "." + method.getName() + "()");

      final var result = method.invoke(target, args);

      logTrace.end(begin);
      return result;
    } catch (final Exception e) {
      logTrace.exception(begin, e);
      throw e;
    }
  }
}
