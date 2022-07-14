package spring.core.singleton;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import spring.core.singleton.service.StatefulService;

public class StatefulServiceTest {

  @Test
  @DisplayName("singleton bean이 내부 state를 가지게 되고, 값이 업데이트되는 경우 예기치 않는 상황이 발생한다.")
  void singletonTest() {
    // give
    final ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

    final var service1 = ac.getBean(StatefulService.class);
    final var service2 = ac.getBean(StatefulService.class);

    // when
    service1.order("UserA", 10000);
    service2.order("UserB", 20000);

    int price1 = service1.getPrice();
    int price2 = service2.getPrice();

    // then
    assertThat(price1).isEqualTo(price2);
  }

  static class TestConfig {

    @Bean
    public StatefulService statefulService() {
      return new StatefulService();
    }
  }
}
