package spring.advanced.app.v3;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import spring.advanced.trace.TraceStatus;
import spring.advanced.trace.log.LogTrace;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV3 {

  private static final String INVALID_ITEM_ID = "ex";
  private final LogTrace trace;

  public void save(final String itemId) {
    TraceStatus status = null;
    try {
      status = trace.begin("OrderRepositoryV2.save()");

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
