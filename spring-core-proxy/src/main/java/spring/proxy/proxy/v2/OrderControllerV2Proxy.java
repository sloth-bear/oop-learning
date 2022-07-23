package spring.proxy.proxy.v2;


import spring.proxy.app.v2.OrderControllerV2;
import spring.proxy.trace.TraceStatus;
import spring.proxy.trace.logtrace.LogTrace;

public class OrderControllerV2Proxy extends OrderControllerV2 {

  private final OrderControllerV2 target;
  private final LogTrace logTrace;

  public OrderControllerV2Proxy(final OrderControllerV2 target, final LogTrace logTrace) {
    super(null);
    this.target = target;
    this.logTrace = logTrace;
  }

  @Override
  @SuppressWarnings("DuplicatedCode")
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
