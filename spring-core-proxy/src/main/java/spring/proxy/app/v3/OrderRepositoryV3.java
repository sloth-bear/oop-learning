package spring.proxy.app.v3;

import org.springframework.stereotype.Repository;

@Repository
public class OrderRepositoryV3 {

  public void save(final String itemId) {
    if ("ex".equals(itemId)) {
      throw new IllegalStateException("예외 발생!");
    }

    sleep();
  }

  private void sleep() {
    try {
      Thread.sleep(1000);
    } catch (final InterruptedException e) {
      e.printStackTrace();
    }
  }
}
