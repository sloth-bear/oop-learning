package spring.advanced.app.v4;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.advanced.trace.log.LogTrace;
import spring.advanced.trace.log.template.AbstractTemplate;

@Service
@RequiredArgsConstructor
public class OrderServiceV4 {

  private final OrderRepositoryV4 orderRepositoryV4;
  private final LogTrace trace;

  public void orderItem(final String itemId) {
    final var template = new AbstractTemplate<Void>(trace) {
      @Override
      protected Void doProcess() {
        orderRepositoryV4.save(itemId);
        return null;
      }
    };

    template.execute("OrderServiceV4.orderItem()");
  }
}
