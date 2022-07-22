package spring.proxy.pureproxy.proxy.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RealSubject implements Subject {

  @Override
  public String operate() {
    log.info("실제 동작 객체 호출");
    sleep();
    return "OK";
  }

  private void sleep() {
    try {
      Thread.sleep(1000);
    } catch (final InterruptedException e) {
      e.printStackTrace();
    }
  }
}
