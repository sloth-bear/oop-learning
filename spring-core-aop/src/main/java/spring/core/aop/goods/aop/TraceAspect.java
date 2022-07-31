package spring.core.aop.goods.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Slf4j
@Aspect
public class TraceAspect {

  @Before("@annotation(spring.core.aop.goods.annotation.Trace)")
  public void doTrace(final JoinPoint joinPoint) {
    final var args = joinPoint.getArgs();
    log.info("[Trace] {}, args={}", joinPoint.getSignature(), args);
  }
}
