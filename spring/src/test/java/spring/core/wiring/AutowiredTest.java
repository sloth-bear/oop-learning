package spring.core.wiring;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;
import spring.core.member.Member;

public class AutowiredTest {

  @Test
  @SuppressWarnings("resource")
  void autowiredOption() {
    new AnnotationConfigApplicationContext(TestBean.class);
  }

  @SuppressWarnings("SpringJavaAutowiredMembersInspection")
  static class TestBean {

    @Autowired(required = false)
    public void setNoBean1(final Member noBean) {
      System.out.println("Bean 존재하지 않아 애초에 호출되지 않는다.: " + noBean);
    }

    @Autowired
    public void setNoBean2(@Nullable final Member noBean) {
      System.out.println("No bean2: " + noBean);
    }

    @Autowired
    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    public void setNoBean3(final Optional<Member> noBean3) {
      System.out.println("No Bean3: " + noBean3);
    }
  }
}
