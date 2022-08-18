package hello.jdbc.connection;

import static hello.jdbc.connection.ConnectionConst.PASSWORD;
import static hello.jdbc.connection.ConnectionConst.URL;
import static hello.jdbc.connection.ConnectionConst.USERNAME;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.zaxxer.hikari.HikariDataSource;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLTransientConnectionException;
import javax.sql.DataSource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Slf4j
public class ConnectionTest {

  @Test
  void driverManager() throws SQLException {
    final var conn1 = DriverManager.getConnection(URL, USERNAME, PASSWORD);
    final var conn2 = DriverManager.getConnection(URL, USERNAME, PASSWORD);

    log.info("connection = {}, class = {}", conn1, conn1.getClass());
    log.info("connection = {}, class = {}", conn2, conn2.getClass());
    assertThat(conn1).isNotSameAs(conn2);
  }

  @Test
  void dataSourceDriverManager() throws SQLException {
    final DataSource dataSource = new DriverManagerDataSource(URL, USERNAME, PASSWORD);
    final var conn1 = dataSource.getConnection();
    final var conn2 = dataSource.getConnection();

    log.info("connection = {}, class = {}", conn1, conn1.getClass());
    log.info("connection = {}, class = {}", conn2, conn2.getClass());
    assertThat(conn1).isNotSameAs(conn2);
  }

  @Test
  @SuppressWarnings("resource")
  @DisplayName("Connection pooling")
  void dataSourceConnectionPool() throws SQLException, InterruptedException {
    final HikariDataSource dataSource = new HikariDataSource();
    dataSource.setJdbcUrl(URL);
    dataSource.setUsername(USERNAME);
    dataSource.setPassword(PASSWORD);
    dataSource.setMaximumPoolSize(10);
    dataSource.setPoolName("MyPool");

    final var conn1 = dataSource.getConnection();
    final var conn2 = dataSource.getConnection();

    log.info("connection = {}, class = {}", conn1, conn1.getClass());
    log.info("connection = {}, class = {}", conn2, conn2.getClass());

    Thread.sleep(1000); // connection 생성 시간 대기

    assertThat(conn1).isNotSameAs(conn2);
  }

  @SuppressWarnings("resource")
  @Test
  @DisplayName("should block when connection requests exceed than connection pool max size")
  void dataSourceConnectionPoolExceed() {
    final HikariDataSource dataSource = new HikariDataSource();
    dataSource.setJdbcUrl(URL);
    dataSource.setUsername(USERNAME);
    dataSource.setPassword(PASSWORD);
    dataSource.setMaximumPoolSize(10);
    dataSource.setPoolName("MyPool");
    dataSource.setConnectionTimeout(SECONDS.toMillis(10));

    // after 10 seconds (max lifetime)
    assertThatThrownBy(() -> {
      for (int i = 0; i < 11; i++) {
        dataSource.getConnection();
      }
    }).isInstanceOf(SQLTransientConnectionException.class);
  }

}
