package spring.proxy.config.v1_basic.v2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.proxy.app.v2.OrderControllerV2;
import spring.proxy.app.v2.OrderRepositoryV2;
import spring.proxy.app.v2.OrderServiceV2;
import spring.proxy.trace.logtrace.LogTrace;

@Configuration
@SuppressWarnings({"SpringJavaInjectionPointsAutowiringInspection", "SpringFacetCodeInspection"})
public class ConcreteProxyConfig {

  @Bean
  public OrderRepositoryV2 orderRepositoryV2Proxy(final LogTrace logTrace) {
    return new OrderRepositoryV2Proxy(new OrderRepositoryV2(), logTrace);
  }

  @Bean
  public OrderServiceV2 orderServiceV2Proxy(final LogTrace logTrace) {
    return new OrderServiceV2Proxy(
        new OrderServiceV2(orderRepositoryV2Proxy(logTrace)), logTrace
    );
  }

  @Bean
  public OrderControllerV2 orderControllerV2Proxy(final LogTrace logTrace) {
    return new OrderControllerV2Proxy(
        new OrderControllerV2(orderServiceV2Proxy(logTrace)), logTrace
    );
  }
}

