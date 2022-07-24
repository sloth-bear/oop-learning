package spring.proxy.cglib;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.cglib.proxy.Enhancer;
import spring.proxy.cglib.code.TimeMethodInterceptor;
import spring.proxy.common.ServiceConcrete;

@Slf4j
public class CGLIBTest {

  @Test
  void cglib() {
    final var target = new ServiceConcrete();

    final var enhancer = new Enhancer();
    enhancer.setSuperclass(ServiceConcrete.class);
    enhancer.setCallback(new TimeMethodInterceptor(target));

    final var proxy = (ServiceConcrete) enhancer.create();
    log.info("targetClass={}", target.getClass());
    log.info("proxyClass={}", proxy.getClass());
    
    proxy.call();
  }
}
