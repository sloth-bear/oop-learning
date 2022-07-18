package spring.core.discount;

import static spring.core.member.Grade.VIP;

import org.springframework.stereotype.Component;
import spring.core.annotation.MainDiscountPolicy;
import spring.core.member.Member;

@Component
@MainDiscountPolicy
public class RateDiscountPolicy implements DiscountPolicy {

  private static final int DISCOUNT_RATE = 10;

  @Override
  public int getDiscountAmount(final Member member, final int price) {
    return member.getGrade() == VIP ? price * DISCOUNT_RATE / 100 : 0;
  }

}
