package spring.proxy.config.v1_basic.v1;

import lombok.RequiredArgsConstructor;
import spring.proxy.app.v1.OrderServiceV1;
import spring.proxy.trace.TraceStatus;
import spring.proxy.trace.logtrace.LogTrace;


@RequiredArgsConstructor
public class OrderServiceV1Proxy implements OrderServiceV1 {

  private final OrderServiceV1 target;
  private final LogTrace logTrace;

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
