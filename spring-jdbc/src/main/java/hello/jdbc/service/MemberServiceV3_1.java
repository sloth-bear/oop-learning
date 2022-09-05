package hello.jdbc.service;

import hello.jdbc.domain.Member;
import hello.jdbc.repository.MemberRepositoryV3;
import java.sql.SQLException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.DefaultTransactionDefinition;

/**
 * 트랜잭션 - 트랜잭션 매니저 사용
 */
@Slf4j
@RequiredArgsConstructor
public class MemberServiceV3_1 {

  private final PlatformTransactionManager transactionManager;
  private final MemberRepositoryV3 memberRepository;

  public void transferAccount(final String fromId, final String toId, final int money) {
    final var status = transactionManager.getTransaction(new DefaultTransactionDefinition());

    try {
      doBusinessLogic(fromId, toId, money);
      transactionManager.commit(status);
    } catch (final Exception e) {
      transactionManager.rollback(status);
      throw new IllegalStateException(e);
    }
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
