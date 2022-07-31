package spring.core.aop.goods.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import spring.core.aop.goods.annotation.Retry;

@Slf4j
@Aspect
public class RetryAspect {

  @Around("@annotation(retry)")
  public Object doRetry(final ProceedingJoinPoint joinPoint, final Retry retry) throws Throwable {
    log.info("[Retry] {}, retry={}", joinPoint.getSignature(), retry);

    final var maxRetry = retry.value();
    Exception exceptionHolder = null;

    for (int retryCount = 1; retryCount <= maxRetry; retryCount++) {
      try {
        log.info("[Retry] try re request, count={}/{}", retryCount, maxRetry);
        return joinPoint.proceed();
      } catch (final Exception e) {
        exceptionHolder = e;
      }
    }

    return exceptionHolder;
  }
}
