package spring.proxy.pureproxy.concreteproxy;

import org.junit.jupiter.api.Test;
import spring.proxy.pureproxy.concreteproxy.code.Concrete;
import spring.proxy.pureproxy.concreteproxy.code.ConcreteClient;
import spring.proxy.pureproxy.concreteproxy.code.TimeDecorator;

public class ConcreteProxyTest {

  @Test
  void noProxy() {
    final var client = new ConcreteClient(new Concrete());
    client.execute();
  }

  @Test
  void proxy() {
    final var client = new ConcreteClient(new TimeDecorator(new Concrete()));
    client.execute();
  }
}
