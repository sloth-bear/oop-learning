package spring.core.wiring;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.core.AutoAppConfig;
import spring.core.discount.DiscountPolicy;
import spring.core.discount.FixDiscountPolicy;
import spring.core.discount.RateDiscountPolicy;
import spring.core.member.Grade;
import spring.core.member.Member;

@SuppressWarnings("resource")
public class AllBeanTest {

  @Test
  void doNotPrintBeanFields() {
    new AnnotationConfigApplicationContext(DiscountService.class);
  }

  @Test
  void shouldPrintAutowiredAllBeanByField() {
    new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);
  }

  @Test
  void shouldDiscountByGivenPolicyName() {
    final var ac = new AnnotationConfigApplicationContext(AutoAppConfig.class,
        DiscountService.class);

    final var discountService = ac.getBean(DiscountService.class);
    final var member = new Member(1L, "member", Grade.VIP);

    final var fixedDiscountPrice = discountService.discount(member, 10000, "fixDiscountPolicy");
    final var rateDiscountPrice = discountService.discount(member, 10000, "rateDiscountPolicy");

    assertThat(discountService).isInstanceOf(DiscountService.class);
    assertThat(fixedDiscountPrice).isEqualTo(1000);
    assertThat(rateDiscountPrice).isEqualTo(1000);
  }

  @Test
  void shouldDiscountByGivenPolicyClass() {
    final var ac = new AnnotationConfigApplicationContext(AutoAppConfig.class,
        DiscountService.class);

    final var discountService = ac.getBean(DiscountService.class);
    final var member = new Member(1L, "member", Grade.VIP);

    final var fixedDiscountPrice = discountService.discount(member, 10000, FixDiscountPolicy.class);
    final var rateDiscountPrice = discountService.discount(member, 10000, RateDiscountPolicy.class);

    assertThat(discountService).isInstanceOf(DiscountService.class);
    assertThat(fixedDiscountPrice).isEqualTo(1000);
    assertThat(rateDiscountPrice).isEqualTo(1000);
  }

  static class DiscountService {

    private final Map<String, DiscountPolicy> policyMap;
    private final List<DiscountPolicy> policies;

    public DiscountService(final Map<String, DiscountPolicy> policyMap,
        final List<DiscountPolicy> policies) {
      System.out.println(policies);
      System.out.println(policyMap);
      this.policyMap = policyMap;
      this.policies = policies;
    }

    public int discount(final Member member, final int price, final String policyName) {
      final var policy = this.policyMap.get(policyName);
      return policy.getDiscountAmount(member, price);
    }

    public int discount(final Member member, final int price,
        final Class<? extends DiscountPolicy> className) {
      final var policy = this.policies.stream()
          .filter(p -> Objects.equals(className, p.getClass()))
          .findAny()
          .orElseThrow(() -> new IllegalArgumentException("There is no policy: " + className));
      return policy.getDiscountAmount(member, price);
    }
  }

}
