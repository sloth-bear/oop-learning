package spring.core.aop.order;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OrderService {

  private final OrderRepository orderRepository;

  public OrderService(final OrderRepository orderRepository) {
    this.orderRepository = orderRepository;
  }

  public void saveOrderItem(final String itemId) {
    log.info("[OrderService] 실행");
    orderRepository.save(itemId);
  }
}
