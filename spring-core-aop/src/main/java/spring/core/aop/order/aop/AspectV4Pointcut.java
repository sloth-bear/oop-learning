package spring.core.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Slf4j
@Aspect
public class AspectV4Pointcut {


  @Around("spring.core.aop.order.aop.PointCut.allOrder()")
  public Object doLog(final ProceedingJoinPoint joinPoint) throws Throwable {
    log.info("[LOG] {}", joinPoint.getSignature());

    return joinPoint.proceed();
  }

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

}
