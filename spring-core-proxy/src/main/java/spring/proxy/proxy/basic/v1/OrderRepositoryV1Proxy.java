package spring.proxy.proxy.basic.v1;

import lombok.RequiredArgsConstructor;
import spring.proxy.app.v1.OrderRepositoryV1;
import spring.proxy.trace.TraceStatus;
import spring.proxy.trace.logtrace.LogTrace;

@RequiredArgsConstructor
public class OrderRepositoryV1Proxy implements OrderRepositoryV1 {

  private final OrderRepositoryV1 target;
  private final LogTrace logTrace;

  @Override
  public void save(final String itemId) {
    TraceStatus begin = null;
    try {
      begin = logTrace.begin("OrderRepository.save()");

      target.save(itemId);

      logTrace.end(begin);
    } catch (final Exception e) {
      logTrace.exception(begin, e);
      throw e;
    }
  }
}
