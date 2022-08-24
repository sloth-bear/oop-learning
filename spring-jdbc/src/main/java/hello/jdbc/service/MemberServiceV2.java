package hello.jdbc.service;

import hello.jdbc.domain.Member;
import hello.jdbc.repository.MemberRepositoryV2;
import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 트랜잭션 - 파라미터로 연동, pool 고려한 종료 (autocommit - 기본값으로 돌려주어야 한다.)
 */
@Slf4j
@RequiredArgsConstructor
public class MemberServiceV2 {

  private final DataSource dataSource;
  private final MemberRepositoryV2 memberRepository;

  public void transferAccount(
      final String fromId, final String toId, final int money) throws SQLException {
    final var conn = dataSource.getConnection();

    try {
      conn.setAutoCommit(false);

      doBusinessLogic(conn, fromId, toId, money);

      conn.commit();
    } catch (final Exception e) {
      conn.rollback();
      throw new IllegalStateException(e);
    } finally {
      release(conn);
    }
  }

  private void doBusinessLogic(
      final Connection conn, final String fromId, final String toId, final int money) {
    final var fromMember = memberRepository.findById(conn, fromId);
    final var toMember = memberRepository.findById(conn, toId);

    memberRepository.update(conn, fromId, fromMember.getMoney() - money);

    validate(toMember);

    memberRepository.update(conn, toId, toMember.getMoney() + money);
  }

  private void release(final Connection conn) {
    if (conn != null) {
      try {
        conn.setAutoCommit(true);
        conn.close();
      } catch (final Exception e) {
        log.info("There is an error to close connection", e);
      }
    }
  }

  private void validate(final Member toMember) {
    if ("failMember".equals(toMember.getId())) {
      throw new IllegalStateException("이체 중 예외 발생");
    }
  }
}
