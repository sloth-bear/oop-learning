package spring.proxy.app.v2;

public class OrderRepositoryV2 {

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
