package spring.advanced.trace.threadlocal.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadLocalService {

  private final ThreadLocal<String> nameHolder = new ThreadLocal<>();

  public void logic(final String name) {
    log.info("name 저장={} --> nameHolder={}", name, nameHolder.get());
    nameHolder.set(name);
    sleep();
    log.info("name 조회={} --> nameHolder={}", name, nameHolder.get());
  }

  private void sleep() {
    try {
      Thread.sleep(1000);
    } catch (final InterruptedException e) {
      e.printStackTrace();
    }
  }
}
