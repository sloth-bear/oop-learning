package hello.jdbc.repository;

import hello.jdbc.connection.DBConnectionUtil;
import hello.jdbc.domain.Member;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.NoSuchElementException;
import lombok.extern.slf4j.Slf4j;

/**
 * JDBC - DriverManager 사용한 repository layer
 */
@Slf4j
public class MemberRepositoryV0 {

  public Member insert(final Member member) {
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
      throw new RuntimeException(e);
    } finally {
      close(conn, pstmt, null);
    }
  }

  public Member findById(final String id) {
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
      throw new RuntimeException(e);
    } finally {
      close(conn, pstmt, rs);
    }
  }

  @SuppressWarnings("UnusedReturnValue")
  public Member update(final String id, final Member member) {
    final var sql = "UPDATE member SET money= ? WHERE id = ?";

    Connection conn = null;
    PreparedStatement pstmt = null;

    try {
      conn = getConnection();
      pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1, member.getMoney());
      pstmt.setString(2, id);
      final int resultSize = pstmt.executeUpdate();
      log.info("result size = {}", resultSize);
      return member;
    } catch (final SQLException e) {
      log.error("db error", e);
      throw new RuntimeException(e);
    } finally {
      close(conn, pstmt, null);
    }
  }

  public void deleteById(final String id) {
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
      throw new RuntimeException(e);
    } finally {
      close(conn, pstmt, null);
    }
  }

  @SuppressWarnings("SameParameterValue")
  private void close(final Connection conn, final Statement stmt, final ResultSet rs) {
    if (rs != null) {
      try {
        rs.close();
      } catch (final SQLException e) {
        log.info("error", e);
      }
    }

    if (stmt != null) {
      try {
        stmt.close();
      } catch (final SQLException e) {
        log.info("error", e);
      }
    }

    if (conn != null) {
      try {
        conn.close();
      } catch (final SQLException e) {
        log.info("error", e);
      }
    }
  }

  private Connection getConnection() {
    return DBConnectionUtil.getConnection();
  }

}
