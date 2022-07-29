package spring.core.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Slf4j
@Aspect
public class AspectV6Advice {

  @SuppressWarnings("DuplicatedCode")
  @Around("spring.core.aop.order.aop.PointCut.allOrderAndService()")
  public Object doTransaction(final ProceedingJoinPoint joinPoint) throws Throwable {
    try {
      log.info("[트랜잭션 시작] {}", joinPoint.getSignature());

      final Object result = joinPoint.proceed();

      log.info("[트랜잭션 커밋] {}", joinPoint.getSignature());
      return result;
    } catch (final Exception e) {
      log.info("[트랜잭션 롤백] {}", joinPoint.getSignature());
      throw e;
    } finally {
      log.info("[리소스 릴리즈] {}", joinPoint.getSignature());
    }
  }

  @Before("spring.core.aop.order.aop.PointCut.allOrderAndService()")
  public void doBefore(final JoinPoint joinPoint) {
    log.info("[Before] {}", joinPoint.getSignature());
  }

  @AfterReturning(value = "spring.core.aop.order.aop.PointCut.allOrderAndService()", returning = "result")
  public void doAfterReturning(final JoinPoint joinPoint, final Object result) {
    log.info("[After Returning] {}, return: {}", joinPoint.getSignature(), result);
  }

  @AfterThrowing(value = "spring.core.aop.order.aop.PointCut.allOrderAndService()", throwing = "ex")
  public void doAfterThrowing(final JoinPoint joinPoint, final Exception ex) {
    log.info("[After Throwing] {}, exception: {}", joinPoint.getSignature(), ex);
  }

  @After("spring.core.aop.order.aop.PointCut.allOrderAndService()")
  public void doAfter(final JoinPoint joinPoint) {
    log.info("[After] {}", joinPoint.getSignature());
  }

}
