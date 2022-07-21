package spring.advanced.app.v5;

import org.springframework.stereotype.Repository;
import spring.advanced.trace.log.LogTrace;
import spring.advanced.trace.log.callback.TraceTemplate;

@Repository
public class OrderRepositoryV5 {

  private static final String INVALID_ITEM_ID = "ex";
  private final TraceTemplate template;

  public OrderRepositoryV5(final LogTrace logTrace) {
    this.template = new TraceTemplate(logTrace);
  }

  public void save(final String itemId) {
    template.execute("OrderRepositoryV5.save()", () -> {
      if (INVALID_ITEM_ID.equals(itemId)) {
        throw new IllegalStateException("예외");
      }

      sleep();
      return null;
    });
  }

  private void sleep() {
    try {
      Thread.sleep(1000);
    } catch (final InterruptedException e) {
      e.printStackTrace();
    }
  }
}
