package spring.proxy.common.advice;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

@Slf4j
public class TimeAdvice implements MethodInterceptor {


  @Override
  public Object invoke(final MethodInvocation invocation) throws Throwable {
    log.info("TimeProxy execute");
    final var start = System.currentTimeMillis();

    final var result = invocation.proceed();

    final var end = System.currentTimeMillis();
    final var resultTime = end - start;
    log.info("TimeProxy end, resultTime = {}", resultTime);
    return result;
  }
}
