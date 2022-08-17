package hello.jdbc.connection;

import static hello.jdbc.connection.ConnectionConst.PASSWORD;
import static hello.jdbc.connection.ConnectionConst.URL;
import static hello.jdbc.connection.ConnectionConst.USERNAME;
import static org.assertj.core.api.Assertions.assertThat;

import java.sql.DriverManager;
import java.sql.SQLException;
import javax.sql.DataSource;
import lombok.extern.slf4j.Slf4j;
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
  
}
