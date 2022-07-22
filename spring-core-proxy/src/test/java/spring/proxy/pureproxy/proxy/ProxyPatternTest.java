package spring.proxy.pureproxy.proxy;

import org.junit.jupiter.api.Test;
import spring.proxy.pureproxy.proxy.code.CacheProxy;
import spring.proxy.pureproxy.proxy.code.ProxyPatternClient;
import spring.proxy.pureproxy.proxy.code.RealSubject;

public class ProxyPatternTest {

  @Test
  void noProxyTest() {
    final var client = new ProxyPatternClient(new RealSubject());
    client.execute();
    client.execute();
    client.execute();
  }

  @Test
  void cacheProxyTest() {
    final var client = new ProxyPatternClient(new CacheProxy(new RealSubject()));
    client.execute();
    client.execute();
    client.execute();
  }
}
