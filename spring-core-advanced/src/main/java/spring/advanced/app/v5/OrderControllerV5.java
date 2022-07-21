package spring.advanced.app.v5;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.advanced.trace.log.LogTrace;
import spring.advanced.trace.log.callback.TraceTemplate;

@RestController
public class OrderControllerV5 {

  private final OrderServiceV5 orderServiceV5;
  private final TraceTemplate template;

  public OrderControllerV5(final OrderServiceV5 orderServiceV5, final LogTrace trace) {
    this.orderServiceV5 = orderServiceV5;
    this.template = new TraceTemplate(trace);
  }

  @GetMapping("/v5/orders")
  public String getOrderItem(final String itemId) {
    return template.execute(
        "OrderControllerV5.getOrderItem()",
        () -> {
          orderServiceV5.orderItem(itemId);
          return "OK";
        }
    );
  }
}
