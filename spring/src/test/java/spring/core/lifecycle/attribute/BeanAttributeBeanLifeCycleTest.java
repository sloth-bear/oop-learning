package spring.core.lifecycle.attribute;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanAttributeBeanLifeCycleTest {

  @Test
  void lifeCycleTest() {
    final ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(
        LifeCycleConfig.class);
    ac.close();
  }

  @Configuration
  static class LifeCycleConfig {

    @Bean(initMethod = "init", destroyMethod = "close")
    public NetworkClient networkClient() {
      final var networkClient = new NetworkClient();
      networkClient.setUrl("https://hello-spring.dev");
      return networkClient;
    }
  }

}
