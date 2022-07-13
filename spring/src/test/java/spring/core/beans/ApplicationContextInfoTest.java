package spring.core.beans;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.core.AppConfig;

public class ApplicationContextInfoTest {

  AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
      AppConfig.class);

  @Test
  @DisplayName("모든 빈 출력")
  void findAllBean() {
    String[] definedBeanNames = context.getBeanDefinitionNames();
    for (final var beanName : definedBeanNames) {
      Object bean = context.getBean(beanName);
      System.out.println("name = " + beanName + " object = " + bean);
    }
  }

  @Test
  @DisplayName("애플리케이션 빈 출력")
  void findApplicationBean() {
    final var beanDefinitionNames = context.getBeanDefinitionNames();
    for (final var beanName : beanDefinitionNames) {
      final var beanDefinition = context.getBeanDefinition(beanName);

      if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
        final var bean = context.getBean(beanName);
        System.out.println("name = " + beanName + " object = " + bean);
      }
    }
  }
}
