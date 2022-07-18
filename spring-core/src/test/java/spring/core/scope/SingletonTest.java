package spring.core.scope;

import static org.assertj.core.api.Assertions.assertThat;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

public class SingletonTest {

  @Test
  void findSingletonBean() {
    final AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(
        SingletonBean.class);
    
    final var bean1 = ac.getBean(SingletonBean.class);
    final var bean2 = ac.getBean(SingletonBean.class);

    assertThat(bean1).isSameAs(bean2);
    ac.close();
  }

  @Scope("singleton")
  static class SingletonBean {

    @PostConstruct
    public void init() {
      System.out.println("SingletonBean.init");
    }

    @PreDestroy
    public void destroy() {
      System.out.println("SingletonBean.destroy");
    }
  }
}
