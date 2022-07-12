package spring.core.member;

import java.util.HashMap;
import java.util.Map;

public class MemoryMemberRepository implements MemberRepository {

  // 동시성 이슈가 있을 수 있으므로 concurrentHashMap 쓰는 것이 맞다.
  private static final Map<Long, Member> STORE = new HashMap<>();

  @Override
  public void save(Member member) {
    STORE.put(member.getId(), member);
  }

  @Override
  public Member findById(Long memberId) {
    return STORE.get(memberId);
  }

}
