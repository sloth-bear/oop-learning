package hello.jdbc.repository;

import hello.jdbc.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
class MemberRepositoryV0Test {

  MemberRepositoryV0 repositoryV0 = new MemberRepositoryV0();

  @Test
  void insert() {
    final var member = new Member("memberV0", 10000);
    repositoryV0.save(member);
  }
}