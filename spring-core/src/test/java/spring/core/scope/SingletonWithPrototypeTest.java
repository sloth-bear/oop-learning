package spring.core.scope;

import static org.assertj.core.api.Assertions.assertThat;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

@SuppressWarnings("resource")
public class SingletonWithPrototypeTest {

  @Test
  void findPrototype() {
    final AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(
        PrototypeBean.class);

    final var prototypeBean1 = ac.getBean(PrototypeBean.class);
    prototypeBean1.addCount();
    assertThat(prototypeBean1.getCount()).isEqualTo(1);

    final var prototypeBean2 = ac.getBean(PrototypeBean.class);
    prototypeBean2.addCount();
    assertThat(prototypeBean2.getCount()).isEqualTo(1);
  }

  @Test
  void singletonClientUsingPrototype() {
    final AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(
        SingletonBean.class, PrototypeBean.class);

    final var singletonBean1 = ac.getBean(SingletonBean.class);
    final var count1 = singletonBean1.logic();
    assertThat(count1).isEqualTo(1);

    final var singletonBean2 = ac.getBean(SingletonBean.class);
    final var count2 = singletonBean2.logic();
    assertThat(count2).isEqualTo(2);
  }

  @Test
  void singletonClientUsingPrototypeByDependencyLookup() {
    final AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(
        SingletonBean.class, PrototypeBean.class);

    final var singletonBean1 = ac.getBean(SingletonBean.class);
    final var count1 = singletonBean1.logicUsingDPLookup();
    assertThat(count1).isEqualTo(1);

    final var singletonBean2 = ac.getBean(SingletonBean.class);
    final var count2 = singletonBean2.logicUsingDPLookup();
    assertThat(count2).isEqualTo(1);
  }

  @Test
  void singletonClientUsingPrototypeByObjectProvider() {
    final AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(
        SingletonBean.class, PrototypeBean.class);

    final var singletonBean1 = ac.getBean(SingletonBean.class);
    final var count1 = singletonBean1.logicUsingObjectProvider();
    assertThat(count1).isEqualTo(1);

    final var singletonBean2 = ac.getBean(SingletonBean.class);
    final var count2 = singletonBean2.logicUsingObjectProvider();
    assertThat(count2).isEqualTo(1);
  }

  @Test
  @DisplayName("JSR-330")
  void singletonClientUsingPrototypeByProvider() {
    final AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(
        SingletonBean.class, PrototypeBean.class);

    final var singletonBean1 = ac.getBean(SingletonBean.class);
    final var count1 = singletonBean1.logicUsingProvider();
    assertThat(count1).isEqualTo(1);

    final var singletonBean2 = ac.getBean(SingletonBean.class);
    final var count2 = singletonBean2.logicUsingProvider();
    assertThat(count2).isEqualTo(1);
  }

  @Scope("prototype")
  static class PrototypeBean {

    private int count = 0;

    public void addCount() {
      count++;
    }

    public int getCount() {
      return count;
    }

    @PostConstruct
    public void init() {
      System.out.println("PrototypeBean.init: " + this);
    }

    @PreDestroy
    public void destroy() {
      System.out.println("PrototypeBean.destroy");
    }
  }

  @Scope("singleton")
  @RequiredArgsConstructor
  static class SingletonBean {

    private final PrototypeBean prototypeBean;

    private final ApplicationContext ac;

    private final ObjectProvider<PrototypeBean> prototypeBeanObjectProvider;

    private final Provider<PrototypeBean> prototypeBeanProvider;

    public int logic() {
      prototypeBean.addCount();
      return prototypeBean.getCount();
    }

    public int logicUsingDPLookup() {
      final var bean = ac.getBean(PrototypeBean.class);
      bean.addCount();
      return bean.getCount();
    }

    public int logicUsingObjectProvider() {
      final PrototypeBean bean = prototypeBeanObjectProvider.getObject();
      bean.addCount();
      return bean.getCount();
    }

    public int logicUsingProvider() {
      final PrototypeBean bean = prototypeBeanProvider.get();
      bean.addCount();
      return bean.getCount();
    }
  }
}
