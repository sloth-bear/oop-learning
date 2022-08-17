package hello.jdbc.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import hello.jdbc.domain.Member;
import java.util.NoSuchElementException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
class MemberRepositoryV0Test {

  MemberRepositoryV0 repositoryV0 = new MemberRepositoryV0();

  @Test
  void crud() {
    // create
    final var member = new Member("memberV100", 10000);
    final var savedMember = repositoryV0.insert(member);
    assertThat(savedMember).isNotNull();

    // read
    final var storedMember = repositoryV0.findById(savedMember.getId());
    log.info("storedMember = {}", storedMember);
    assertThat(storedMember).isNotNull();
    assertThat(storedMember).isEqualTo(savedMember);

    // update
    final var memberForUpdate = new Member(savedMember.getId(), 20000);
    repositoryV0.update(savedMember.getId(), memberForUpdate);
    final var updatedMember = repositoryV0.findById(savedMember.getId());
    assertThat(updatedMember).isNotNull();
    assertThat(updatedMember).isEqualTo(memberForUpdate);

    // delete
    repositoryV0.deleteById(updatedMember.getId());
    assertThatThrownBy(() -> repositoryV0.findById(updatedMember.getId()))
        .isInstanceOf(NoSuchElementException.class);
  }

}