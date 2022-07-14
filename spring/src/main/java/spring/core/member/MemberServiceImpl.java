package spring.core.member;

public class MemberServiceImpl implements MemberService {

  private final MemberRepository memberRepository;

  public MemberServiceImpl(final MemberRepository memberRepository) {
    this.memberRepository = memberRepository;
  }

  @Override
  public boolean join(final Member member) {
    memberRepository.save(member);
    return true;
  }

  @Override
  public Member find(final Long memberId) {
    return memberRepository.findById(memberId);
  }
  
  public MemberRepository getMemberRepository() {
    return memberRepository;
  }
}
