package spring.advanced.app.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.advanced.trace.TraceStatus;
import spring.advanced.trace.log.LogTraceV1;

@Service
@RequiredArgsConstructor
public class OrderServiceV1 {

  private final OrderRepositoryV1 orderRepositoryV1;
  private final LogTraceV1 trace;

  public void orderItem(final String itemId) {
    TraceStatus status = null;
    try {
      status = trace.begin("OrderServiceV1.orderItem()");
      orderRepositoryV1.save(itemId);
      trace.end(status);
    } catch (final Exception e) {
      assert status != null;
      trace.exception(status, e);
      throw e;
    }
  }
}
