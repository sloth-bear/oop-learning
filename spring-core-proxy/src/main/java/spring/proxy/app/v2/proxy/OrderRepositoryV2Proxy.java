package spring.proxy.app.v2.proxy;

import spring.proxy.app.v2.OrderRepositoryV2;
import spring.proxy.trace.TraceStatus;
import spring.proxy.trace.logtrace.LogTrace;

public class OrderRepositoryV2Proxy extends OrderRepositoryV2 {

  private final OrderRepositoryV2 target;
  private final LogTrace logTrace;

  public OrderRepositoryV2Proxy(final OrderRepositoryV2 target, final LogTrace logTrace) {
    this.target = target;
    this.logTrace = logTrace;
  }

  @Override
  @SuppressWarnings("DuplicatedCode")
  public void save(final String itemId) {
    TraceStatus begin = null;
    try {
      begin = logTrace.begin("OrderRepository2.save()");

      target.save(itemId);

      logTrace.end(begin);
    } catch (final Exception e) {
      logTrace.exception(begin, e);
      throw e;
    }
  }
}
