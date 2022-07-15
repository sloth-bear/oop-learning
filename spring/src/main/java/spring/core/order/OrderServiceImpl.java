package spring.core.order;

import org.springframework.stereotype.Component;
import spring.core.discount.DiscountPolicy;
import spring.core.member.MemberRepository;


@Component
public class OrderServiceImpl implements OrderService {

  private final MemberRepository memberRepository;
  private final DiscountPolicy discountPolicy;

  public OrderServiceImpl(final MemberRepository memberRepository,
      final DiscountPolicy discountPolicy) {
    this.memberRepository = memberRepository;
    this.discountPolicy = discountPolicy;
  }

  @Override
  public Order create(final Long memberId, final String goodsName, final int goodsPrice) {
    final var orderedMember = memberRepository.findById(memberId);

    return new Order(
        1L,
        goodsName,
        goodsPrice,
        discountPolicy.getDiscountAmount(orderedMember, goodsPrice));
  }

  public MemberRepository getMemberRepository() {
    return memberRepository;
  }

}
