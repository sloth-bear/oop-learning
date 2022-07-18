package spring.advanced.app.v0;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV0 {

  private final OrderServiceV0 orderServiceV0;

  @GetMapping("/v0/orders")
  public String getOrderItem(final String itemId) {
    orderServiceV0.orderItem(itemId);
    return "OK";
  }
}
