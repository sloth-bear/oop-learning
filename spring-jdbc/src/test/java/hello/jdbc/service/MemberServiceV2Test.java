package hello.jdbc.service;

import static hello.jdbc.connection.ConnectionConst.PASSWORD;
import static hello.jdbc.connection.ConnectionConst.URL;
import static hello.jdbc.connection.ConnectionConst.USERNAME;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import hello.jdbc.domain.Member;
import hello.jdbc.repository.MemberRepositoryV2;
import java.sql.SQLException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * Transaction 구현을 위해 connection 파라미터 전달 방식으로 동기화한다.
 */
@Slf4j
class MemberServiceV2Test {

  private static final String MEMBER_A = "memberA";
  private static final String MEMBER_B = "memberB";
  private static final String FAIL_MEMBER = "failMember";

  private MemberRepositoryV2 memberRepository;
  private MemberServiceV2 memberService;

  @BeforeEach
  void setup() {
    final DriverManagerDataSource datasource = new DriverManagerDataSource(URL, USERNAME,
        PASSWORD);

    memberRepository = new MemberRepositoryV2(datasource);
    memberService = new MemberServiceV2(datasource, memberRepository);
  }

  @AfterEach
  void teardown() {
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
  @DisplayName("입금할 대상에게 해당하는 금액 만큼 이체하는 중 예외가 발생할 경우 트랜잭션이 없어서 데이터의 원자성이 위배된다.")
  void should_thrown_when_transfer_account() {
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

}