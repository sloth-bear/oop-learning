package spring.core.bean.definition;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.core.AppConfig;

public class BeanDefinitionTest {

  AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

  @Test
  @DisplayName("빈 설정 메타정보 확인할 수 있다.")
  void beanMetadata() {
    final var beanDefinitionNames = ac.getBeanDefinitionNames();

    for (final var name : beanDefinitionNames) {
      final var definition = ac.getBeanDefinition(name);

      if (definition.getRole() == BeanDefinition.ROLE_APPLICATION) {
        System.out.println("beanDefinitionName = " + name + ", metadata = " + definition);
      }

    }
  }
}
