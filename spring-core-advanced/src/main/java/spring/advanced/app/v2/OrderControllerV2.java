package spring.advanced.app.v2;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.advanced.trace.TraceStatus;
import spring.advanced.trace.proto.ProtoLogTraceV2;

@RestController
@RequiredArgsConstructor
public class OrderControllerV2 {

  private final OrderServiceV2 orderServiceV2;
  private final ProtoLogTraceV2 trace;

  @GetMapping("/v2/orders")
  public String getOrderItem(final String itemId) {
    TraceStatus status = null;
    try {
      status = trace.begin("OrderControllerV2.getOrderItem()");
      orderServiceV2.orderItem(itemId, status.getTraceId());
      trace.end(status);
      return "OK";
    } catch (final Exception e) {
      assert status != null;
      trace.exception(status, e);
      throw e;
    }
  }
}
