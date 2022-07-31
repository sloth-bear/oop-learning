package spring.core.aop.internalcall.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Slf4j
@Aspect
public class CallLogAspect {

  @Before("execution(* spring.core.aop.internalcall..*.*(..))")
  public void doLog(final JoinPoint joinPoint) {
    log.info("call aop={}", joinPoint.getSignature());
  }

}
