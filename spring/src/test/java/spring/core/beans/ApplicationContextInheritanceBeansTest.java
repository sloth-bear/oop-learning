package spring.core.beans;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.core.discount.DiscountPolicy;
import spring.core.discount.FixDiscountPolicy;
import spring.core.discount.RateDiscountPolicy;

public class ApplicationContextInheritanceBeansTest {

  AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
      TestConfig.class);

  @Test
  @DisplayName("자식이 둘 이상 존재하는 부모 타입으로 조회 시 예외 발생")
  void byTwoChildrenType() {
    assertThrows(NoSuchBeanDefinitionException.class, () -> context.getBean(DiscountPolicy.class));
  }

  @Test
  @DisplayName("자식이 둘 이상 존재하는 부모 타입으로 조회 시 자식 이름을 지정해줌으로써 빈을 조회할 수 있다.")
  void byTwoChildrenTypeWithChildrenName() {
    final var rateDiscountPolicy = context.getBean("rateDiscountPolicy", DiscountPolicy.class);
    assertThat(rateDiscountPolicy).isInstanceOf(RateDiscountPolicy.class);
  }

  @Test
  @DisplayName("자식이 둘 이상 존재하는 부모 타입으로 조회 시 구현 타입으로 조회할 경우 빈을 조회할 수 있다.")
  void byTwoChildrenTypeWithImplementationName() {
    final var rateDiscountPolicy = context.getBean("rateDiscountPolicy", RateDiscountPolicy.class);
    assertThat(rateDiscountPolicy).isInstanceOf(RateDiscountPolicy.class);
  }

  @Test
  @DisplayName("부모 타입으로 모든 빈을 조회할 수 있다.")
  void allBeansByParentType() {
    final var allBeans = context.getBeansOfType(DiscountPolicy.class);
    assertThat(allBeans.size()).isEqualTo(2);
  }

  @Configuration
  static class TestConfig {

    @Bean
    public DiscountPolicy rateDiscountPolicy() {
      return new RateDiscountPolicy();
    }

    @Bean
    public DiscountPolicy fixDiscountPolicy() {
      return new FixDiscountPolicy();
    }
  }

}
