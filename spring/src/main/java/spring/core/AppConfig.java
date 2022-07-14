package spring.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.core.discount.DiscountPolicy;
import spring.core.discount.FixDiscountPolicy;
import spring.core.member.MemberRepository;
import spring.core.member.MemberService;
import spring.core.member.MemberServiceImpl;
import spring.core.member.MemoryMemberRepository;
import spring.core.order.OrderService;
import spring.core.order.OrderServiceImpl;

@Configuration
@SuppressWarnings("SpringFacetCodeInspection")
public class AppConfig {

  @Bean
  public MemberService memberService() {
    System.out.println("call memberService");
    return new MemberServiceImpl(memberRepository());
  }

  @Bean
  public OrderService orderService() {
    System.out.println("call orderService");
    return new OrderServiceImpl(
        memberRepository(),
        discountPolicy()
    );
  }

  @Bean
  public MemberRepository memberRepository() {
    System.out.println("new memberRepository");
    return new MemoryMemberRepository();
  }

  @Bean
  public DiscountPolicy discountPolicy() {
    return new FixDiscountPolicy();
  }

}
