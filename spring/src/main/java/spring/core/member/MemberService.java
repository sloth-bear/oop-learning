package spring.core.member;

public interface MemberService {

  Member find(Long memberId);

  boolean join(Member member);

}
