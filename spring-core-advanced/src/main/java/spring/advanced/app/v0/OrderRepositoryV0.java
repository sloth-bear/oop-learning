package spring.advanced.app.v0;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV0 {

  private static final String INVALID_ITEM_ID = "ex";

  public void save(final String itemId) {
    if (INVALID_ITEM_ID.equals(itemId)) {
      throw new IllegalStateException("예외");
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
