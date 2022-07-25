package spring.proxy.proxy.factory;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.proxy.app.v2.OrderControllerV2;
import spring.proxy.app.v2.OrderRepositoryV2;
import spring.proxy.app.v2.OrderServiceV2;
import spring.proxy.proxy.factory.advice.LogTraceAdvice;
import spring.proxy.trace.logtrace.LogTrace;

@Slf4j
@Configuration
public class ProxyFactoryConfigV2 {

  @Bean
  public OrderRepositoryV2 orderRepositoryV2(final LogTrace logTrace) {
    final var target = new OrderRepositoryV2();
    final var factory = new ProxyFactory(target);
    factory.addAdvisor(getAdvisor(logTrace));

    final OrderRepositoryV2 proxy = (OrderRepositoryV2) factory.getProxy();
    log.info("ProxyFactory proxy={}, target={}", proxy.getClass(), target.getClass());
    return proxy;
  }

  @Bean
  public OrderServiceV2 orderServiceV2(final LogTrace logTrace) {
    final var target = new OrderServiceV2(orderRepositoryV2(logTrace));
    final var factory = new ProxyFactory(target);
    factory.addAdvisor(getAdvisor(logTrace));

    final OrderServiceV2 proxy = (OrderServiceV2) factory.getProxy();
    log.info("ProxyFactory proxy={}, target={}", proxy.getClass(), target.getClass());
    return proxy;
  }

  @Bean
  public OrderControllerV2 orderControllerV2(final LogTrace logTrace) {
    final var target = new OrderControllerV2(orderServiceV2(logTrace));
    final var factory = new ProxyFactory(target);
    factory.addAdvisor(getAdvisor(logTrace));

    final OrderControllerV2 proxy = (OrderControllerV2) factory.getProxy();
    log.info("ProxyFactory proxy={}, target={}", proxy.getClass(), target.getClass());
    return proxy;
  }

  private Advisor getAdvisor(final LogTrace logTrace) {
    final var pointcut = new NameMatchMethodPointcut();
    pointcut.setMappedNames("orders*", "save*");

    return new DefaultPointcutAdvisor(pointcut, new LogTraceAdvice(logTrace));
  }

}
