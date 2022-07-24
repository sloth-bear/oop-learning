package spring.proxy.proxyfactory;

import static org.assertj.core.api.Assertions.assertThat;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.AopUtils;
import spring.proxy.common.ServiceConcrete;
import spring.proxy.common.ServiceImpl;
import spring.proxy.common.ServiceInterface;
import spring.proxy.common.advice.TimeAdvice;

@Slf4j
public class ProxyFactoryTest {

  @Test
  @DisplayName("인터페이스가 존재할 경우 SpringFactory는 JDK 동적 프록시를 사용한다.")
  void interfaceProxy() {
    final var target = new ServiceImpl();
    final var proxyFactory = new ProxyFactory(target);
    proxyFactory.addAdvice(new TimeAdvice());

    final var proxy = (ServiceInterface) proxyFactory.getProxy();
    log.info("target class: {}", target.getClass());
    log.info("proxy clasS:{}", proxy.getClass());

    proxy.save();

    assertThat(AopUtils.isAopProxy(proxy)).isTrue();
    assertThat(AopUtils.isJdkDynamicProxy(proxy)).isTrue();
    assertThat(AopUtils.isCglibProxy(proxy)).isFalse();
  }

  @Test
  @DisplayName("구체클래스만 존재할 경우 CGLIB 프록시를 사용한다.")
  void concreteProxy() {
    final var target = new ServiceConcrete();
    final var proxyFactory = new ProxyFactory(target);
    proxyFactory.addAdvice(new TimeAdvice());

    final var proxy = (ServiceConcrete) proxyFactory.getProxy();
    log.info("target class: {}", target.getClass());
    log.info("proxy clasS:{}", proxy.getClass());

    proxy.call();

    assertThat(AopUtils.isAopProxy(proxy)).isTrue();
    assertThat(AopUtils.isJdkDynamicProxy(proxy)).isFalse();
    assertThat(AopUtils.isCglibProxy(proxy)).isTrue();
  }

  @Test
  @DisplayName("ProxyTargetClass 옵션을 사용하면 인터페이스가 존재해도 CGLIB를 사용하고, class 기반 프록시를 사용할 수 있다.")
  void proxyTargetClass() {
    final var target = new ServiceImpl();
    final var proxyFactory = new ProxyFactory(target);
    proxyFactory.setProxyTargetClass(true);
    proxyFactory.addAdvice(new TimeAdvice());

    final var proxy = (ServiceInterface) proxyFactory.getProxy();
    log.info("target class: {}", target.getClass());
    log.info("proxy clasS:{}", proxy.getClass());

    proxy.save();

    assertThat(AopUtils.isAopProxy(proxy)).isTrue();
    assertThat(AopUtils.isJdkDynamicProxy(proxy)).isFalse();
    assertThat(AopUtils.isCglibProxy(proxy)).isTrue();
  }
}
