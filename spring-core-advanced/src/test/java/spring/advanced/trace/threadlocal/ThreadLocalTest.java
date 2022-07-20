package spring.advanced.trace.threadlocal;

import org.junit.jupiter.api.Test;
import spring.advanced.trace.threadlocal.code.ThreadLocalService;

public class ThreadLocalTest {

  private final ThreadLocalService threadLocalService = new ThreadLocalService();

  @Test
  void safeConcurrency() {
    final Runnable userA = () -> threadLocalService.logic("userA");
    final Runnable userB = () -> threadLocalService.logic("userB");

    final var threadA = new Thread(userA);
    threadA.setName("thread-A");

    final var threadB = new Thread(userB);
    threadB.setName("thread-B");

    threadA.start();
    sleep(2000);
    threadB.start();
    sleep(3000);
  }

  @Test
  void concurrencyIssue() {
    final Runnable userA = () -> threadLocalService.logic("userA");
    final Runnable userB = () -> threadLocalService.logic("userB");

    final var threadA = new Thread(userA);
    threadA.setName("thread-A");

    final var threadB = new Thread(userB);
    threadB.setName("thread-B");

    threadA.start();
    sleep(100);
    threadB.start();
    sleep(3000);
  }

  private void sleep(final int milliseconds) {
    try {
      Thread.sleep(milliseconds);
    } catch (final InterruptedException e) {
      e.printStackTrace();
    }
  }
}
