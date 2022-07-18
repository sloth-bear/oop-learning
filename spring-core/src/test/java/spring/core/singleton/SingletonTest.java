package spring.core.singleton;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.core.AppConfig;
import spring.core.member.MemberRepository;
import spring.core.member.MemberService;
import spring.core.member.MemberServiceImpl;
import spring.core.order.OrderServiceImpl;
import spring.core.singleton.service.SingletonService;

public class SingletonTest {

  @Test
  @DisplayName("pure DI Container request test")
  void pureContainer() {
    final var appConfig = new AppConfig();

    final var memberService1 = appConfig.memberService();
    final var memberService2 = appConfig.memberService();

    System.out.println("1: " + memberService1);
    System.out.println("2: " + memberService2);

    assertThat(memberService1).isNotSameAs(memberService2);
  }

  @Test
  @DisplayName("singleton pattern")
  void singletonServiceTest() {
    final var service1 = SingletonService.getInstance();
    final var service2 = SingletonService.getInstance();

    assertThat(service1).isSameAs(service2);
  }

  @Test
  @SuppressWarnings("resource")
  @DisplayName("SpringContainer and singleton")
  void springContainer() {
    final var context = new AnnotationConfigApplicationContext(AppConfig.class);

    final var memberService1 = context.getBean("memberService", MemberService.class);
    final var memberService2 = context.getBean("memberService", MemberService.class);

    System.out.println("1: " + memberService1);
    System.out.println("2: " + memberService2);

    assertThat(memberService1).isSameAs(memberService2);
  }

  @Test
  @SuppressWarnings("resource")
  @DisplayName("Spring container configuration singleton bean test")
  void configurationSingletonTest() {
    final var context = new AnnotationConfigApplicationContext(AppConfig.class);

    final MemberServiceImpl memberService = context.getBean(MemberServiceImpl.class);
    final OrderServiceImpl orderService = context.getBean(OrderServiceImpl.class);
    final MemberRepository memberRepository = context.getBean(MemberRepository.class);

    assertThat(memberService.getMemberRepository()).isSameAs(orderService.getMemberRepository());
    assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
    assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);
  }

  @Test
  @SuppressWarnings("resource")
  void configurationCGLIB() {
    final var context = new AnnotationConfigApplicationContext(AppConfig.class);
    final var bean = context.getBean(AppConfig.class);

    System.out.println(bean.getClass());
  }

}
