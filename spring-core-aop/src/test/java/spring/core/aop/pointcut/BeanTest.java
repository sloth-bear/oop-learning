package spring.core.aop.pointcut;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import spring.core.aop.order.OrderService;

@Slf4j
@SpringBootTest
@Import(BeanTest.BeanAspect.class)
public class BeanTest {

  @Autowired
  OrderService orderService;

  @Test
  void success() {
    orderService.saveOrderItem("itemA");
  }

  @Aspect
  static class BeanAspect {

    @Around("bean(orderService) || bean(*Repository)")
    public Object doLog(final ProceedingJoinPoint joinPoint) throws Throwable {
      log.info("[bean] {}", joinPoint.getSignature());
      return joinPoint.proceed();
    }
  }

}
