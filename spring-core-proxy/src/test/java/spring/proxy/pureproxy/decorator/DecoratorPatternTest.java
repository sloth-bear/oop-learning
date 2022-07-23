package spring.proxy.pureproxy.decorator;

import org.junit.jupiter.api.Test;
import spring.proxy.pureproxy.decorator.code.DecoratorPatternClient;
import spring.proxy.pureproxy.decorator.code.MessageDecorator;
import spring.proxy.pureproxy.decorator.code.RealComponent;
import spring.proxy.pureproxy.decorator.code.TimeDecorator;

public class DecoratorPatternTest {

  @Test
  void noDecorator() {
    final var client = new DecoratorPatternClient(new RealComponent());
    client.execute();
  }

  @Test
  void decorator() {
    final var client = new DecoratorPatternClient(new MessageDecorator(new RealComponent()));
    client.execute();
  }

  @Test
  void proxyChaining() {
    final var client = new DecoratorPatternClient(
        new TimeDecorator(new MessageDecorator(new RealComponent())));
    client.execute();
  }
}
