package spring.proxy.app.v1.proxy;


import lombok.RequiredArgsConstructor;
import spring.proxy.app.v1.OrderControllerV1;
import spring.proxy.trace.TraceStatus;
import spring.proxy.trace.logtrace.LogTrace;

@RequiredArgsConstructor
public class OrderControllerV1Proxy implements OrderControllerV1 {

  private final OrderControllerV1 target;
  private final LogTrace logTrace;

  @Override
  public String orders(final String itemId) {
    TraceStatus begin = null;
    try {
      begin = logTrace.begin("OrderService.save()");

      final var result = target.orders(itemId);

      logTrace.end(begin);
      return result;
    } catch (final Exception e) {
      logTrace.exception(begin, e);
      throw e;
    }
  }

  @Override
  public String noLogs() {
    return target.noLogs();
  }
}
