package spring.core.lifecycle.implementation;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class InterfaceBeanLifeCycleTest {

  @Test
  void lifeCycleTest() {
    final ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(
        LifeCycleConfig.class);
    ac.close();
  }

  @Configuration
  static class LifeCycleConfig {

    @Bean
    public NetworkClient networkClient() {
      final var networkClient = new NetworkClient();
      networkClient.setUrl("https://hello-spring.dev");
      return networkClient;
    }
  }
}