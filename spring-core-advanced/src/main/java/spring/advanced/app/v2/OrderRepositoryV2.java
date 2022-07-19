package spring.advanced.app.v2;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import spring.advanced.trace.TraceId;
import spring.advanced.trace.TraceStatus;
import spring.advanced.trace.proto.ProtoLogTraceV2;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV2 {

  private static final String INVALID_ITEM_ID = "ex";
  private final ProtoLogTraceV2 trace;

  public void save(final String itemId, final TraceId traceId) {
    TraceStatus status = null;
    try {
      status = trace.begin(traceId, "OrderRepositoryV2.save()");

      if (INVALID_ITEM_ID.equals(itemId)) {
        throw new IllegalStateException("예외");
      }

      sleep();

      trace.end(status);
    } catch (final Exception e) {
      assert status != null;
      trace.exception(status, e);
      throw e;
    }
  }

  private void sleep() {
    try {
      Thread.sleep(1000);
    } catch (final InterruptedException e) {
      e.printStackTrace();
    }
  }
}
