package hello.jdbc.service;

import hello.jdbc.domain.Member;
import hello.jdbc.repository.MemberRepositoryV3;
import java.sql.SQLException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

/**
 * 트랜잭션 - AOP
 */
@Slf4j
@Transactional
public class MemberServiceV3_3 {

  private final MemberRepositoryV3 memberRepository;

  public MemberServiceV3_3(final MemberRepositoryV3 memberRepository) {
    this.memberRepository = memberRepository;
  }

  public void transferAccount(final String fromId, final String toId, final int money)
      throws SQLException {
    doBusinessLogic(fromId, toId, money);
  }

  private void doBusinessLogic(final String fromId, final String toId, final int money)
      throws SQLException {
    final var fromMember = memberRepository.findById(fromId);
    final var toMember = memberRepository.findById(toId);

    memberRepository.update(fromId, fromMember.getMoney() - money);

    validate(toMember);

    memberRepository.update(toId, toMember.getMoney() + money);
  }

  private void validate(final Member toMember) {
    if ("failMember".equals(toMember.getId())) {
      throw new IllegalStateException("이체 중 예외 발생");
    }
  }
}
