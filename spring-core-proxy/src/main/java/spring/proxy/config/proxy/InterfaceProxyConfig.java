package spring.proxy.config.proxy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.proxy.app.v1.OrderControllerV1;
import spring.proxy.app.v1.OrderControllerV1Impl;
import spring.proxy.app.v1.OrderRepositoryV1;
import spring.proxy.app.v1.OrderRepositoryV1Impl;
import spring.proxy.app.v1.OrderServiceV1;
import spring.proxy.app.v1.OrderServiceV1Impl;
import spring.proxy.proxy.v1.OrderControllerV1Proxy;
import spring.proxy.proxy.v1.OrderRepositoryV1Proxy;
import spring.proxy.proxy.v1.OrderServiceV1Proxy;
import spring.proxy.trace.logtrace.LogTrace;

@Configuration
public class InterfaceProxyConfig {

  @Bean
  public OrderControllerV1 orderControllerV1Proxy(final LogTrace logTrace) {
    return new OrderControllerV1Proxy(
        new OrderControllerV1Impl(orderServiceV1Proxy(logTrace)), logTrace
    );
  }

  @Bean
  public OrderServiceV1 orderServiceV1Proxy(final LogTrace logTrace) {
    return new OrderServiceV1Proxy(
        new OrderServiceV1Impl(orderRepositoryV1Proxy(logTrace)), logTrace
    );
  }

  @Bean
  public OrderRepositoryV1 orderRepositoryV1Proxy(final LogTrace logTrace) {
    return new OrderRepositoryV1Proxy(new OrderRepositoryV1Impl(), logTrace);
  }

}
