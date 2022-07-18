package spring.core.lifecycle.plain;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class PlainBeanLifecycleTest {

  @Test
  void lifeCycleTest() {
    final ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(
        LifeCycleConfig.class);
    final var client = ac.getBean(NetworkClient.class);
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
