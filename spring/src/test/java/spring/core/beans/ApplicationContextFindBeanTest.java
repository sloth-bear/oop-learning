package spring.core.beans;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.core.AppConfig;
import spring.core.member.MemberService;
import spring.core.member.MemberServiceImpl;

public class ApplicationContextFindBeanTest {

  AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
      AppConfig.class);

  @Test
  @DisplayName("존재하지 않는 빈 이름 조회")
  void byNonName() {
    assertThrows(NoSuchBeanDefinitionException.class, () -> context.getBean("XXX"));
  }

  @Test
  @DisplayName("빈 이름으로 조회")
  void by() {
    MemberService memberService = context.getBean("memberService", MemberService.class);
    assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
  }

  @Test
  @DisplayName("빈 타입으로 조회")
  void byType() {
    MemberService memberService = context.getBean(MemberService.class);
    assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
  }

  @Test
  @DisplayName("구체 타입으로 조회")
  void byImplType() {
    MemberService memberService = context.getBean(MemberServiceImpl.class);
    assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
  }

}
