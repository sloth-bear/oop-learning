package spring.advanced.app.v4;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.advanced.trace.log.LogTrace;
import spring.advanced.trace.log.templtae.AbstractTemplate;

@RestController
@RequiredArgsConstructor
public class OrderControllerV4 {

  private final OrderServiceV4 orderServiceV4;
  private final LogTrace trace;

  @GetMapping("/v4/orders")
  public String getOrderItem(final String itemId) {
    final var template = new AbstractTemplate<String>(trace) {
      @Override
      protected String doProcess() {
        orderServiceV4.orderItem(itemId);
        return "OK";
      }
    };

    return template.execute("OrderControllerV4.getOrderItem()");
  }
}
