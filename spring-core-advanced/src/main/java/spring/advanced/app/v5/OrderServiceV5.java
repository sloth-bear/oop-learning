package spring.advanced.app.v5;

import org.springframework.stereotype.Service;
import spring.advanced.trace.log.LogTrace;
import spring.advanced.trace.log.callback.TraceTemplate;

@Service
public class OrderServiceV5 {

  private final OrderRepositoryV5 orderRepositoryV5;
  private final TraceTemplate template;

  public OrderServiceV5(final OrderRepositoryV5 orderRepositoryV5, final LogTrace logTrace) {
    this.orderRepositoryV5 = orderRepositoryV5;
    this.template = new TraceTemplate(logTrace);
  }

  public void orderItem(final String itemId) {
    template.execute("OrderServiceV5.orderItem()", () -> {
      orderRepositoryV5.save(itemId);
      return null;
    });
  }
}
