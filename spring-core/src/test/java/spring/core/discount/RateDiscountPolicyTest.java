package spring.core.discount;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import spring.core.member.Grade;
import spring.core.member.Member;

class RateDiscountPolicyTest {

  private final RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

  @Test
  @DisplayName("VIP 등급의 회원은 10% 할인이 적용되어야 한다.")
  void shouldReturn10PercentDiscountedPriceIfVIPGrade() {
    // given
    final var member = new Member(1L, "VIP", Grade.VIP);

    // when
    final var discountAmount = discountPolicy.getDiscountAmount(member, 10000);

    // then
    assertEquals(discountAmount, 1000);
  }

  @Test
  @DisplayName("VIP 등급이 아닌 회원의 경우 할인이 적용되지 않는다.")
  void shouldReturnUnDiscountedPriceIfBasicGrade() {
    // given
    final var member = new Member(1L, "VIP", Grade.BASIC);

    // when
    final var discountAmount = discountPolicy.getDiscountAmount(member, 10000);

    // then
    assertEquals(discountAmount, 0);
  }

}