package spring.core.member;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static spring.core.member.Grade.BASIC;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import spring.core.AppConfig;

public class MemberServiceTest {

  private MemberService memberService;

  @BeforeEach
  public void init() {
    final var appConfig = new AppConfig();
    memberService = appConfig.memberService();
  }

  @Test
  public void join() {
    // give
    final var member = new Member(1L, "TestMember", BASIC);

    // when
    final var result = memberService.join(member);

    // then
    assertTrue(result);
  }

}
