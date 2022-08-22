package hello.jdbc.service;

import hello.jdbc.domain.Member;
import hello.jdbc.repository.MemberRepositoryV1;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MemberServiceV1 {

  private final MemberRepositoryV1 memberRepository;

  public void transferAccount(final String fromId, final String toId, final int money) {
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
