package spring.proxy.pureproxy.concreteproxy.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TimeDecorator extends Concrete {

  private final Concrete concrete;

  public TimeDecorator(final Concrete concrete) {
    this.concrete = concrete;
  }

  @Override
  public String operate() {
    log.info("TimeDecorator execute");
    final var start = System.currentTimeMillis();

    final var result = concrete.operate();

    final var end = System.currentTimeMillis();
    final var resultTime = end - start;
    log.info("ResultTime: {}ms", resultTime);
    return result;
  }

}
