package spring.proxy.proxy.v2;

import spring.proxy.app.v2.OrderServiceV2;
import spring.proxy.trace.TraceStatus;
import spring.proxy.trace.logtrace.LogTrace;


public class OrderServiceV2Proxy extends OrderServiceV2 {

  private final OrderServiceV2 target;
  private final LogTrace logTrace;

  public OrderServiceV2Proxy(
      final OrderServiceV2 target,
      final LogTrace logTrace) {
    super(null); // proxy를 사용하기 때문에 사용하지 않아 null 주입
    this.target = target;
    this.logTrace = logTrace;
  }

  @Override
  @SuppressWarnings("DuplicatedCode")
  public void saveItem(final String itemId) {
    TraceStatus begin = null;
    try {
      begin = logTrace.begin("OrderService.saveItem()");

      target.saveItem(itemId);

      logTrace.end(begin);
    } catch (final Exception e) {
      logTrace.exception(begin, e);
      throw e;
    }
  }
}
