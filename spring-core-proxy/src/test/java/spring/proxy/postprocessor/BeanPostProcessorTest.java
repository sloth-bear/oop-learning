package spring.proxy.postprocessor;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
public class BeanPostProcessorTest {

  @Test
  @SuppressWarnings("resource")
  void basicConfig() {
    final var ac = new AnnotationConfigApplicationContext(BeanPostProcessConfig.class);

    final var beanA = ac.getBean("beanA", B.class);
    beanA.helloB();

    assertThatThrownBy(() -> ac.getBean(A.class))
        .isInstanceOf(NoSuchBeanDefinitionException.class);
  }

  @Slf4j
  static class BeanAToBPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(final Object bean, final String beanName)
        throws BeansException {
      log.info("beanName={}, bean={}", beanName, bean);
      if (bean instanceof A) {
        return new B();
      }

      return bean;
    }
  }

  @Slf4j
  @Configuration
  static class BeanPostProcessConfig {

    @Bean(name = "beanA")
    public A a() {
      return new A();
    }

    @Bean
    public BeanPostProcessor beanPostProcessor() {
      return new BeanAToBPostProcessor();
    }
  }

  @Slf4j
  static class A {

    @SuppressWarnings("unused")
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