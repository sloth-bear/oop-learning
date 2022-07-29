package spring.core.aop;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import spring.core.aop.order.OrderRepository;
import spring.core.aop.order.OrderService;
import spring.core.aop.order.aop.AspectV1;

@Slf4j
@SpringBootTest
@Import(AspectV1.class)
public class AopTest {

  @Autowired
  OrderService orderService;

  @Autowired
  OrderRepository orderRepository;

  @Test
  void logAopInfo() {
    log.info("isAopProxy, orderService={}", AopUtils.isAopProxy(orderRepository));
    log.info("isAopProxy, orderService={}", AopUtils.isAopProxy(orderService));
  }

  @Test
  void success() {
    orderService.saveOrderItem("itemA");
  }

  @Test
  void exception() {
    Assertions.assertThatThrownBy(() -> orderService.saveOrderItem("ex"))
        .isInstanceOf(IllegalStateException.class);
  }
}
