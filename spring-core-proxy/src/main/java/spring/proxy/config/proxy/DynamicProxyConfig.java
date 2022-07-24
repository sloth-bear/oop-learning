package spring.proxy.config.proxy;

import java.lang.reflect.Proxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.proxy.app.v1.OrderControllerV1;
import spring.proxy.app.v1.OrderControllerV1Impl;
import spring.proxy.app.v1.OrderRepositoryV1;
import spring.proxy.app.v1.OrderRepositoryV1Impl;
import spring.proxy.app.v1.OrderServiceV1;
import spring.proxy.app.v1.OrderServiceV1Impl;
import spring.proxy.proxy.dynamic.LogTraceHandler;
import spring.proxy.trace.logtrace.LogTrace;

@Configuration
@SuppressWarnings({"SpringJavaInjectionPointsAutowiringInspection", "SpringFacetCodeInspection"})
public class DynamicProxyConfig {

  @Bean
  public OrderRepositoryV1 orderRepositoryV1(final LogTrace logTrace) {
    final OrderRepositoryV1 orderRepository = new OrderRepositoryV1Impl();

    return (OrderRepositoryV1) Proxy.newProxyInstance(
        OrderRepositoryV1.class.getClassLoader(),
        new Class[]{OrderRepositoryV1.class},
        new LogTraceHandler(orderRepository, logTrace)
    );
  }

  @Bean
  public OrderServiceV1 orderServiceV1(final LogTrace logTrace) {
    final OrderServiceV1 orderService = new OrderServiceV1Impl(orderRepositoryV1(logTrace));

    return (OrderServiceV1) Proxy.newProxyInstance(
        OrderServiceV1.class.getClassLoader(),
        new Class[]{OrderServiceV1.class},
        new LogTraceHandler(orderService, logTrace)
    );
  }

  @Bean
  public OrderControllerV1 orderControllerV1(final LogTrace logTrace) {
    final OrderControllerV1 orderController = new OrderControllerV1Impl(orderServiceV1(logTrace));

    return (OrderControllerV1) Proxy.newProxyInstance(
        OrderControllerV1.class.getClassLoader(),
        new Class[]{OrderControllerV1.class},
        new LogTraceHandler(orderController, logTrace)
    );
  }

}
