package spring.proxy.app.v2;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderServiceV2 {

  private final OrderRepositoryV2 orderRepositoryV2;

  public void saveItem(final String itemId) {
    orderRepositoryV2.save(itemId);
  }
}
