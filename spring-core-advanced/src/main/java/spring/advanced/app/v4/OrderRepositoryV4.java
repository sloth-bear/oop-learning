package spring.advanced.app.v4;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import spring.advanced.trace.log.LogTrace;
import spring.advanced.trace.log.templtae.AbstractTemplate;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV4 {

  private static final String INVALID_ITEM_ID = "ex";
  private final LogTrace trace;

  public void save(final String itemId) {
    final var template = new AbstractTemplate<Void>(trace) {
      @Override
      protected Void doProcess() {
        if (INVALID_ITEM_ID.equals(itemId)) {
          throw new IllegalStateException("예외");
        }

        sleep();
        return null;
      }
    };

    template.execute("OrderRepositoryV4.save()");
  }

  private void sleep() {
    try {
      Thread.sleep(1000);
    } catch (final InterruptedException e) {
      e.printStackTrace();
    }
  }
}
