package spring.proxy.app.v1;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderServiceV1Impl implements OrderServiceV1{

  private final OrderRepositoryV1 orderRepositoryV1;
  @Override
  public void saveItem(String itemId) {
    orderRepositoryV1.save(itemId);
  }
}
