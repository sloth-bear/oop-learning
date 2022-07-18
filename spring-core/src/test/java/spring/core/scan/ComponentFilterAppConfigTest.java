package spring.core.scan;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import spring.core.scan.filter.ExcludeBean;
import spring.core.scan.filter.IncludeBean;
import spring.core.scan.filter.MyExcludeComponent;
import spring.core.scan.filter.MyIncludeComponent;

public class ComponentFilterAppConfigTest {

  private final ApplicationContext ac = new AnnotationConfigApplicationContext(
      ComponentFilterAppConfig.class);

  @Test
  void filterScan() {
    final var includeBean = ac.getBean("includeBean", IncludeBean.class);

    assertThat(includeBean).isNotNull();
    assertThrows(
        NoSuchBeanDefinitionException.class, () -> ac.getBean("excludeBean", ExcludeBean.class)
    );
  }

  @Configuration
  @ComponentScan(
      includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = MyIncludeComponent.class),
      excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = MyExcludeComponent.class)
  )
  static class ComponentFilterAppConfig {

  }
}
