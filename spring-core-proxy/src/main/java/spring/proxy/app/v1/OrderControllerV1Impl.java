package spring.proxy.app.v1;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderControllerV1Impl implements OrderControllerV1{

  private final OrderServiceV1 orderServiceV1;

  @Override
  public String orders(String itemId) {
    orderServiceV1.saveItem(itemId);
    return "OK";
  }

  @Override
  public String noLogs() {
    return "OK";
  }
}
