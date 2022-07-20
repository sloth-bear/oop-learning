package spring.advanced.app.v3;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.advanced.trace.TraceStatus;
import spring.advanced.trace.log.LogTrace;

@Service
@RequiredArgsConstructor
public class OrderServiceV3 {

  private final OrderRepositoryV3 orderRepositoryV3;
  private final LogTrace trace;

  public void orderItem(final String itemId) {
    TraceStatus status = null;
    try {
      status = trace.begin("OrderServiceV2.orderItem()");
      orderRepositoryV3.save(itemId);
      trace.end(status);
    } catch (final Exception e) {
      assert status != null;
      trace.exception(status, e);
      throw e;
    }
  }
}
