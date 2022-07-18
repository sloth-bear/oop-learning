package spring.core.lifecycle.annotation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class AnnotationBeanLifeCycleTest {

  @Test
  @DisplayName("JSR-250 방법으로 Bean life cycle을 관리할 수 있다. Spring에서 권장한다.")
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
