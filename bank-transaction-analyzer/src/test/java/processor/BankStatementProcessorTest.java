package processor;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Test;
import parser.BankTransaction;

class BankStatementProcessorTest {

  @Test
  void should_return_max_transaction_between_dates() {
    // given
    final var transactions = List.of(
        new BankTransaction(LocalDate.now(), 3000, "이천 원"),
        new BankTransaction(LocalDate.now(), 2000, "이천 원"),
        new BankTransaction(LocalDate.now(), 4000, "이천 원"),
        new BankTransaction(LocalDate.now().plusDays(2), 5000, "이천 원")
    );

    final var processor = new BankStatementProcessor(transactions);

    final var startDate = LocalDate.now().minusDays(1);
    final var endDate = LocalDate.now().plusDays(1);

    // when
    final var result = processor.findMaxAmount(startDate, endDate);
    final var expected = new BankTransaction(LocalDate.now(), 4000, "이천 원");

    // then
    assertThat(result).isEqualTo(expected);
  }

  @Test
  void should_return_min_transaction_between_dates() {
    // given
    final var transactions = List.of(
        new BankTransaction(LocalDate.now(), 3000, "이천 원"),
        new BankTransaction(LocalDate.now(), 2000, "이천 원"),
        new BankTransaction(LocalDate.now(), 4000, "이천 원"),
        new BankTransaction(LocalDate.now().plusDays(2), 1000, "이천 원")
    );

    final var processor = new BankStatementProcessor(transactions);

    final var startDate = LocalDate.now().minusDays(1);
    final var endDate = LocalDate.now().plusDays(1);

    // when
    final var result = processor.findMinAmount(startDate, endDate);
    final var expected = new BankTransaction(LocalDate.now(), 2000, "이천 원");

    // then
    assertThat(result).isEqualTo(expected);
  }
}