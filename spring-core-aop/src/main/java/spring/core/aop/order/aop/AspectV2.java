package spring.core.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Slf4j
@Aspect
public class AspectV2 {


  @Around("allOrderPointcutSignature()")
  public Object doLog(final ProceedingJoinPoint joinPoint) throws Throwable {
    log.info("[LOG] {}", joinPoint.getSignature());

    return joinPoint.proceed();
  }

  @Pointcut("execution(* spring.core.aop.order..*(..))")
  private void allOrderPointcutSignature() {
  }

}
