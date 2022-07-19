package spring.advanced.app.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.advanced.trace.TraceStatus;
import spring.advanced.trace.proto.ProtoLogTraceV1;

@RestController
@RequiredArgsConstructor
public class OrderControllerV1 {

  private final OrderServiceV1 orderServiceV1;
  private final ProtoLogTraceV1 trace;

  @GetMapping("/v1/orders")
  public String getOrderItem(final String itemId) {
    TraceStatus status = null;
    try {
      status = trace.begin("OrderController.getOrderItem()");
      orderServiceV1.orderItem(itemId);
      trace.end(status);
      return "OK";
    } catch (final Exception e) {
      assert status != null;
      trace.exception(status, e);
      throw e;
    }
  }
}
