package spring.core.scan;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.core.AutoAppConfig;
import spring.core.member.MemberRepository;
import spring.core.member.MemberService;
import spring.core.member.MemberServiceImpl;

public class AutoAppConfigTest {

  private final ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);

  @Test
  void basicScan() {
    final var memberService = ac.getBean(MemberService.class);
    assertThat(memberService).isInstanceOf(MemberService.class);
  }

  @Test
  void autoWiring() {
    final MemberServiceImpl memberService = ac.getBean(MemberServiceImpl.class);
    final var memberRepository = ac.getBean(MemberRepository.class);

    assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
  }
}
