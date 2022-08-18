package hello.jdbc.repository;

import static hello.jdbc.connection.ConnectionConst.PASSWORD;
import static hello.jdbc.connection.ConnectionConst.URL;
import static hello.jdbc.connection.ConnectionConst.USERNAME;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.zaxxer.hikari.HikariDataSource;
import hello.jdbc.domain.Member;
import java.util.NoSuchElementException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@Slf4j
class MemberRepositoryV1Test {

  MemberRepositoryV1 repository;

  @BeforeEach
  void beforeEach() {
//    final DataSource dataSource = new DriverManagerDataSource(URL, USERNAME, PASSWORD);
    final HikariDataSource dataSource = new HikariDataSource();
    dataSource.setJdbcUrl(URL);
    dataSource.setUsername(USERNAME);
    dataSource.setPassword(PASSWORD);
    repository = new MemberRepositoryV1(dataSource);
  }

  @Test
  void crud() throws InterruptedException {
    // create
    final var member = new Member("memberV100", 10000);
    final var savedMember = repository.insert(member);
    assertThat(savedMember).isNotNull();

    // read
    final var storedMember = repository.findById(savedMember.getId());
    log.info("storedMember = {}", storedMember);
    assertThat(storedMember).isNotNull();
    assertThat(storedMember).isEqualTo(savedMember);

    // update
    final var memberForUpdate = new Member(savedMember.getId(), 20000);
    repository.update(savedMember.getId(), memberForUpdate);
    final var updatedMember = repository.findById(savedMember.getId());
    assertThat(updatedMember).isNotNull();
    assertThat(updatedMember).isEqualTo(memberForUpdate);

    // delete
    repository.deleteById(updatedMember.getId());
    assertThatThrownBy(() -> repository.findById(updatedMember.getId()))
        .isInstanceOf(NoSuchElementException.class);

    Thread.sleep(1000);
  }

}