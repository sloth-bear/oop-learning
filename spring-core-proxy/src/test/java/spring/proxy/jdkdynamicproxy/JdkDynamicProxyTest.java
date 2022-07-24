package spring.proxy.jdkdynamicproxy;

import java.lang.reflect.Proxy;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import spring.proxy.jdkdynamicproxy.code.AImpl;
import spring.proxy.jdkdynamicproxy.code.AInterface;
import spring.proxy.jdkdynamicproxy.code.BImpl;
import spring.proxy.jdkdynamicproxy.code.BInterface;
import spring.proxy.jdkdynamicproxy.code.TimeInvocationHandler;

@Slf4j
public class JdkDynamicProxyTest {

  @Test
  void dynamicA() {
    final var target = new AImpl();
    final var handler = new TimeInvocationHandler(target);
    final AInterface proxy = (AInterface) Proxy.newProxyInstance(
        AInterface.class.getClassLoader(), new Class[]{AInterface.class}, handler);

    final var result = proxy.call();
    log.info("result = {}", result);

    log.info("target: {}", target.getClass());
    log.info("handler: {}", handler.getClass());
    log.info("proxy: {}", proxy.getClass()); // AInterface 구현된 proxy
  }

  @Test
  void dynamicB() {
    final var target = new BImpl();
    final var handler = new TimeInvocationHandler(target);
    final BInterface proxy = (BInterface) Proxy.newProxyInstance(
        BInterface.class.getClassLoader(), new Class[]{BInterface.class}, handler);

    final var result = proxy.call();
    log.info("result = {}", result);

    log.info("target: {}", target.getClass());
    log.info("handler: {}", handler.getClass());
    log.info("proxy: {}", proxy.getClass()); // AInterface 구현된 proxy
  }
}
