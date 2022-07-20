package spring.advanced.app.v3;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.advanced.trace.TraceStatus;
import spring.advanced.trace.log.LogTrace;

@RestController
@RequiredArgsConstructor
public class OrderControllerV3 {

  private final OrderServiceV3 orderServiceV3;
  private final LogTrace trace;

  @GetMapping("/v3/orders")
  public String getOrderItem(final String itemId) {
    TraceStatus status = null;
    try {
      status = trace.begin("OrderControllerV2.getOrderItem()");
      orderServiceV3.orderItem(itemId);
      trace.end(status);
      return "OK";
    } catch (final Exception e) {
      assert status != null;
      trace.exception(status, e);
      throw e;
    }
  }
}
