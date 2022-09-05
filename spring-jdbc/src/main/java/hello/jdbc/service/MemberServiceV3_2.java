package hello.jdbc.service;

import hello.jdbc.domain.Member;
import hello.jdbc.repository.MemberRepositoryV3;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * 트랜잭션 - 트랜잭션 Template 사용
 */
@Slf4j
public class MemberServiceV3_2 {

  //  private final PlatformTransactionManager transactionManager;
  private final TransactionTemplate txTemplate;
  private final MemberRepositoryV3 memberRepository;

  public MemberServiceV3_2(final PlatformTransactionManager transactionManager,
      final MemberRepositoryV3 memberRepository) {
    this.txTemplate = new TransactionTemplate(transactionManager);
    this.memberRepository = memberRepository;
  }

  public void transferAccount(final String fromId, final String toId, final int money) {
    txTemplate.executeWithoutResult(status -> doBusinessLogic(fromId, toId, money));
  }

  private void doBusinessLogic(final String fromId, final String toId, final int money) {
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
