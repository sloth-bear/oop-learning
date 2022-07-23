package spring.proxy.pureproxy.decorator.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MessageDecorator extends Decorator {

  public MessageDecorator(final Component component) {
    super(component);
  }

  @Override
  public String operate() {
    log.info("MessageDecorator execute");
    final var data = component.operate();
    return "****** " + data + " ******";
  }
}
