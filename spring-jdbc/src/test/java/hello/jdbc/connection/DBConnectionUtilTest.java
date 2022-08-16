package hello.jdbc.connection;

import static org.assertj.core.api.Assertions.assertThat;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
class DBConnectionUtilTest {

  @Test
  void connectionTest() {
    final var conn = DBConnectionUtil.getConnection();
    assertThat(conn).isNotNull();
  }
}