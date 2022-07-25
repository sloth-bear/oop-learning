package spring.proxy.proxy.factory;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.proxy.app.v1.OrderControllerV1;
import spring.proxy.app.v1.OrderControllerV1Impl;
import spring.proxy.app.v1.OrderRepositoryV1;
import spring.proxy.app.v1.OrderRepositoryV1Impl;
import spring.proxy.app.v1.OrderServiceV1;
import spring.proxy.app.v1.OrderServiceV1Impl;
import spring.proxy.proxy.factory.advice.LogTraceAdvice;
import spring.proxy.trace.logtrace.LogTrace;

@Slf4j
@Configuration
public class ProxyFactoryConfigV1 {

  @Bean
  public OrderRepositoryV1 orderRepositoryV1(final LogTrace logTrace) {
    final var target = new OrderRepositoryV1Impl();
    final var factory = new ProxyFactory(target);
    factory.addAdvisor(getAdvisor(logTrace));

    final OrderRepositoryV1 proxy = (OrderRepositoryV1) factory.getProxy();
    log.info("ProxyFactory proxy={}, target={}", proxy.getClass(), target.getClass());
    return proxy;
  }

  @Bean
  public OrderServiceV1 orderServiceV1(final LogTrace logTrace) {
    final var target = new OrderServiceV1Impl(orderRepositoryV1(logTrace));
    final var factory = new ProxyFactory(target);
    factory.addAdvisor(getAdvisor(logTrace));

    final OrderServiceV1 proxy = (OrderServiceV1) factory.getProxy();
    log.info("ProxyFactory proxy={}, target={}", proxy.getClass(), target.getClass());
    return proxy;
  }

  @Bean
  public OrderControllerV1 orderControllerV1(final LogTrace logTrace) {
    final var target = new OrderControllerV1Impl(orderServiceV1(logTrace));
    final var factory = new ProxyFactory(target);
    factory.addAdvisor(getAdvisor(logTrace));

    final OrderControllerV1 proxy = (OrderControllerV1) factory.getProxy();
    log.info("ProxyFactory proxy={}, target={}", proxy.getClass(), target.getClass());
    return proxy;
  }

  private Advisor getAdvisor(final LogTrace logTrace) {
    final var pointcut = new NameMatchMethodPointcut();
    pointcut.setMappedNames("orders*", "save*");

    return new DefaultPointcutAdvisor(pointcut, new LogTraceAdvice(logTrace));
  }
}
