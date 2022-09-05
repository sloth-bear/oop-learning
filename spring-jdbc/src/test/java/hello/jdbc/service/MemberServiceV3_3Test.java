package hello.jdbc.service;

import static hello.jdbc.connection.ConnectionConst.PASSWORD;
import static hello.jdbc.connection.ConnectionConst.URL;
import static hello.jdbc.connection.ConnectionConst.USERNAME;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import hello.jdbc.domain.Member;
import hello.jdbc.repository.MemberRepositoryV3;
import java.sql.SQLException;
import javax.sql.DataSource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * Transaction 구현을 위해 connection 파라미터 전달 방식으로 동기화한다.
 */
@Slf4j
@SpringBootTest
@SuppressWarnings("NewClassNamingConvention")
class MemberServiceV3_3Test {

  private static final String MEMBER_A = "memberA";
  private static final String MEMBER_B = "memberB";
  private static final String FAIL_MEMBER = "failMember";

  @Autowired
  private MemberRepositoryV3 memberRepository;

  @Autowired
  private MemberServiceV3_3 memberService;

  @AfterEach
  void teardown() throws SQLException {
    memberRepository.deleteById(MEMBER_A);
    memberRepository.deleteById(MEMBER_B);
    memberRepository.deleteById(FAIL_MEMBER);
  }

  @Test
  @DisplayName("입금할 대상에게 해당하는 금액 만큼 정상적으로 이체된다.")
  void transfer_account() throws SQLException {
    //given
    final var memberA = new Member(MEMBER_A, 10000);
    final var memberB = new Member(MEMBER_B, 10000);
    memberRepository.insert(memberA);
    memberRepository.insert(memberB);

    //when
    log.info("START TX");
    memberService.transferAccount(memberA.getId(), memberB.getId(), 2000);
    log.info("END TX");

    //then
    final var resultMemberA = memberRepository.findById(memberA.getId());
    final var resultMemberB = memberRepository.findById(memberB.getId());

    assertThat(resultMemberA.getMoney()).isEqualTo(8000);
    assertThat(resultMemberB.getMoney()).isEqualTo(12000);
  }

  @Test
  @DisplayName("입금할 대상에게 해당하는 금액 만큼 이체하는 중 예외가 발생할 경우 정상적으로 롤백된다.")
  void should_thrown_when_transfer_account() throws SQLException {
    //given
    final var memberA = new Member(MEMBER_A, 10000);
    final var failMember = new Member(FAIL_MEMBER, 10000);
    memberRepository.insert(memberA);
    memberRepository.insert(failMember);

    //when
    assertThatThrownBy(
        () -> memberService.transferAccount(memberA.getId(), failMember.getId(), 2000))
        .isInstanceOf(IllegalStateException.class);

    //then
    final var resultMemberA = memberRepository.findById(memberA.getId());
    final var resultMemberB = memberRepository.findById(failMember.getId());

    assertThat(resultMemberA.getMoney()).isEqualTo(10000);
    assertThat(resultMemberB.getMoney()).isEqualTo(10000);
  }

  @Test
  void checkAop() {
    log.info("memberService class={}", memberService.getClass());
    log.info("memberRepository class={}", memberRepository.getClass());

    assertThat(AopUtils.isAopProxy(memberService)).isTrue();
    assertThat(AopUtils.isAopProxy(memberRepository)).isFalse();
  }

  @TestConfiguration
  static class TestConfig {

    @Bean
    DataSource dataSource() {
      return new DriverManagerDataSource(URL, USERNAME, PASSWORD);
    }

    @Bean
    PlatformTransactionManager transactionManager() {
      return new DataSourceTransactionManager(dataSource());
    }

    @Bean
    public MemberRepositoryV3 memberRepositoryV3() {
      return new MemberRepositoryV3(dataSource());
    }

    @Bean
    public MemberServiceV3_3 memberServiceV3_3() {
      return new MemberServiceV3_3(memberRepositoryV3());
    }
  }

}