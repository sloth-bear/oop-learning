package spring.proxy.pureproxy.decorator.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TimeDecorator extends Decorator {

  public TimeDecorator(final Component component) {
    super(component);
  }

  @Override
  public String operate() {
    log.info("TimeDecorator execute");
    final var start = System.currentTimeMillis();
    final var result = component.operate();
    final var end = System.currentTimeMillis();

    final var resultTime = end - start;
    log.info("TimeDecorator finish resultTime = {}ms", resultTime);
    return result;
  }
}
