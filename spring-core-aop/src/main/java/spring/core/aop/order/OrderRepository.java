package spring.core.aop.order;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class OrderRepository {

  @SuppressWarnings("UnusedReturnValue")
  public String save(final String itemId) {
    log.info("[orderRepository] 실행");

    if ("ex".equals(itemId)) {
      throw new IllegalStateException("예외 발생");
    }
    return "ok";
  }
}
