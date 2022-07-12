package spring.core;

import spring.core.discount.DiscountPolicy;
import spring.core.discount.FixDiscountPolicy;
import spring.core.member.MemberRepository;
import spring.core.member.MemberService;
import spring.core.member.MemberServiceImpl;
import spring.core.member.MemoryMemberRepository;
import spring.core.order.OrderService;
import spring.core.order.OrderServiceImpl;

public class AppConfig {

  private AppConfig() {
    throw new UnsupportedOperationException();
  }

  public static MemberService memberService() {
    return new MemberServiceImpl(memberRepository());
  }

  public static OrderService orderService() {
    return new OrderServiceImpl(
        memberRepository(),
        discountPolicy()
    );
  }

  public static MemberRepository memberRepository() {
    return new MemoryMemberRepository();
  }

  public static DiscountPolicy discountPolicy() {
    return new FixDiscountPolicy();
  }

}
