package spring.advanced.trace.threadlocal;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import spring.advanced.trace.threadlocal.code.FieldService;

@Slf4j
public class FieldServiceTest {

  private final FieldService fieldService = new FieldService();

  @Test
  void safeConcurrency() {
    log.info("main start");
    final Runnable userA = () -> fieldService.logic("userA");
    final Runnable userB = () -> fieldService.logic("userB");

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
    log.info("main start");
    final Runnable userA = () -> fieldService.logic("userA");
    final Runnable userB = () -> fieldService.logic("userB");

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
