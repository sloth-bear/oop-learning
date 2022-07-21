package spring.advanced.app.v2;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.advanced.trace.TraceId;
import spring.advanced.trace.TraceStatus;
import spring.advanced.trace.log.proto.ProtoLogTraceV2;

@Service
@RequiredArgsConstructor
public class OrderServiceV2 {

  private final OrderRepositoryV2 orderRepositoryV2;
  private final ProtoLogTraceV2 trace;

  public void orderItem(final String itemId, final TraceId traceId) {
    TraceStatus status = null;
    try {
      status = trace.begin(traceId, "OrderServiceV2.orderItem()");
      orderRepositoryV2.save(itemId, status.getTraceId());
      trace.end(status);
    } catch (final Exception e) {
      assert status != null;
      trace.exception(status, e);
      throw e;
    }
  }
}
