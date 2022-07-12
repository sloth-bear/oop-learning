package spring.core.order;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import spring.core.AppConfig;
import spring.core.member.Grade;
import spring.core.member.Member;
import spring.core.member.MemberService;

public class OrderServiceTest {

  private MemberService memberService;
  private OrderService orderService;

  @BeforeEach
  public void init() {
    memberService = AppConfig.memberService();
    orderService = AppConfig.orderService();
  }

  @Test
  public void create() {
    // given
    memberService.join(new Member(1L, "NAME", Grade.VIP));

    // when
    final var order = orderService.create(1L, "TEST GOODS", 10000);

    // then
    assertEquals(1000, order.getDiscountAmount());
    assertEquals(9000, order.calculatePrice());
    assertEquals("TEST GOODS", order.getGoodsName());
    assertEquals(10000, order.getGoodsPrice());
  }

}
