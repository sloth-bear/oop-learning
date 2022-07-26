package spring.proxy.postprocessor;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
public class BasicTest {

  @Test
  @SuppressWarnings("resource")
  @DisplayName("스프링 빈으로 등록되지 않은 객체를 빈으로 찾으려 할 경우 예외처리된다.")
  void basicConfig() {
    final var ac = new AnnotationConfigApplicationContext(BasicConfig.class);

    final var beanA = ac.getBean("beanA", A.class);
    beanA.helloA();

    assertThatThrownBy(() -> ac.getBean("beanB", B.class))
        .isInstanceOf(NoSuchBeanDefinitionException.class);
  }

  @Slf4j
  @Configuration
  static class BasicConfig {

    @Bean(name = "beanA")
    public A a() {
      return new A();
    }
  }

  @Slf4j
  static class A {

    public void helloA() {
      log.info("hello A");
    }
  }

  @Slf4j
  static class B {

    @SuppressWarnings("unused")
    public void helloB() {
      log.info("hello B");
    }
  }
}