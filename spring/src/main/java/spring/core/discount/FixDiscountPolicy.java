package spring.core.discount;

import static spring.core.member.Grade.VIP;

import org.springframework.stereotype.Component;
import spring.core.member.Member;

@Component
public class FixDiscountPolicy implements DiscountPolicy {

  private static final int FIX_AMOUNT = 1000;

  @Override
  public int getDiscountAmount(final Member member, final int price) {
    return member.getGrade() == VIP ? FIX_AMOUNT : 0;
  }
}
