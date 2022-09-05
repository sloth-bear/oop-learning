package hello.jdbc.repository;

import static org.springframework.jdbc.support.JdbcUtils.closeResultSet;
import static org.springframework.jdbc.support.JdbcUtils.closeStatement;

import hello.jdbc.domain.Member;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.NoSuchElementException;
import javax.sql.DataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.DataSourceUtils;

/**
 * 아래 util 사용으로 트랜잭션 - 트랜잭션 매니저를 적용한다. DataSourceUtils.getConnection()
 * DataSourceUtils.releaseConnection()
 */
@Slf4j
@SuppressWarnings({"DuplicatedCode", "SqlNoDataSourceInspection", "SqlResolve"})
public class MemberRepositoryV3 {

  private final DataSource dataSource;

  public MemberRepositoryV3(final DataSource dataSource) {
    this.dataSource = dataSource;
  }

  public Member findById(final String id) throws SQLException {
    final var sql = "SELECT * FROM member WHERE id = ?";

    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    try {
      conn = getConnection();
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, id);

      rs = pstmt.executeQuery();

      if (rs.next()) {
        final var member = new Member();
        member.setId(rs.getString("id"));
        member.setMoney(rs.getInt("money"));
        return member;
      }
      throw new NoSuchElementException("member not found, id = " + id);
    } catch (final SQLException e) {
      log.error("db error", e);
      throw e;
    } finally {
      close(conn, pstmt, rs);
    }
  }

  public Member insert(final Member member) throws SQLException {
    final var sql = "INSERT INTO member(id, money) VALUES (?, ?)";

    Connection conn = null;
    PreparedStatement pstmt = null;

    try {
      conn = getConnection();
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, member.getId());
      pstmt.setInt(2, member.getMoney());
      pstmt.executeUpdate();
      return member;
    } catch (final SQLException e) {
      log.error("db error", e);
      throw e;
    } finally {
      close(conn, pstmt, null);
    }
  }

  @SuppressWarnings("UnusedReturnValue")
  public Member update(final String id, final int money) throws SQLException {
    final var sql = "UPDATE member SET money= ? WHERE id = ?";

    Connection conn = null;
    PreparedStatement pstmt = null;

    try {
      conn = getConnection();
      pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1, money);
      pstmt.setString(2, id);
      final int resultSize = pstmt.executeUpdate();
      log.info("result size = {}", resultSize);
      return new Member(id, money);
    } catch (final SQLException e) {
      log.error("db error", e);
      throw e;
    } finally {
      close(conn, pstmt, null);
    }
  }

  public void deleteById(final String id) throws SQLException {
    final var sql = "DELETE FROM member WHERE id = ?";

    Connection conn = null;
    PreparedStatement pstmt = null;

    try {
      conn = getConnection();
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, id);
      final int resultSize = pstmt.executeUpdate();
      log.info("result size = {}", resultSize);
    } catch (final SQLException e) {
      log.error("db error", e);
      throw e;
    } finally {
      close(conn, pstmt, null);
    }
  }

  private void close(final Connection conn, final Statement stmt, final ResultSet rs) {
    closeResultSet(rs);
    closeStatement(stmt);

    // 트랜잭션 동기화를 사용하려면 DataSourceUtils를 사용해야 한다.
    DataSourceUtils.releaseConnection(conn, dataSource);
  }

  /**
   * 트랜잭션 동기화를 사용하기 위해서는 DataSourceUtils를 사용해야 한다.
   */
  private Connection getConnection() throws SQLException {
    final var connection = DataSourceUtils.getConnection(dataSource);
    log.info("getConnection() = {}, class = {}", connection, connection.getClass());
    return connection;
  }

}
