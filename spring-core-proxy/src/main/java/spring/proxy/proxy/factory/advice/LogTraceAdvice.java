package spring.proxy.proxy.factory.advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import spring.proxy.trace.TraceStatus;
import spring.proxy.trace.logtrace.LogTrace;

public class LogTraceAdvice implements MethodInterceptor {

  private final LogTrace logTrace;

  public LogTraceAdvice(final LogTrace logTrace) {
    this.logTrace = logTrace;

  }

  @Override
  public Object invoke(final MethodInvocation invocation) throws Throwable {
    TraceStatus begin = null;
    try {
      final var method = invocation.getMethod();
      begin = logTrace.begin(
          method.getDeclaringClass().getSimpleName() + "." + method.getName() + "()");

      final var result = invocation.proceed();

      logTrace.end(begin);
      return result;
    } catch (final Exception e) {
      logTrace.exception(begin, e);
      throw e;
    }
  }
}
