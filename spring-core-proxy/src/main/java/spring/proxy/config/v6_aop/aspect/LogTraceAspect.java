package spring.proxy.config.v6_aop.aspect;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import spring.proxy.trace.TraceStatus;
import spring.proxy.trace.logtrace.LogTrace;

@Slf4j
@Aspect
public class LogTraceAspect {

  private final LogTrace logTrace;

  public LogTraceAspect(final LogTrace logTrace) {
    this.logTrace = logTrace;
  }

  @Around("execution(* spring.proxy.app..*(..))")
  public Object execute(final ProceedingJoinPoint joinPoint) throws Throwable {
    TraceStatus begin = null;
    try {
      begin = logTrace.begin(joinPoint.getSignature().toShortString());

      final var result = joinPoint.proceed();

      logTrace.end(begin);
      return result;
    } catch (final Exception e) {
      logTrace.exception(begin, e);
      throw e;
    }
  }
}
