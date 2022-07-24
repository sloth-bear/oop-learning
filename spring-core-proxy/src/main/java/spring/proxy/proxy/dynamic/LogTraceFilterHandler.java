package spring.proxy.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import org.springframework.util.PatternMatchUtils;
import spring.proxy.trace.TraceStatus;
import spring.proxy.trace.logtrace.LogTrace;

public class LogTraceFilterHandler implements InvocationHandler {

  private final Object target;
  private final LogTrace logTrace;
  private final String[] patterns;

  public LogTraceFilterHandler(final Object target, final LogTrace logTrace,
      final String[] patterns) {
    this.target = target;
    this.logTrace = logTrace;
    this.patterns = patterns;
  }

  @Override
  @SuppressWarnings("DuplicatedCode")
  public Object invoke(final Object proxy, final Method method, final Object[] args)
      throws Throwable {
    if (!PatternMatchUtils.simpleMatch(patterns, method.getName())) {
      return method.invoke(target, args);
    }

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
