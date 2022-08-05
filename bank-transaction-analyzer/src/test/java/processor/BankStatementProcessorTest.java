package processor;

import static org.assertj.core.api.Assertions.assertThat;

import domain.BankTransaction;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import org.junit.jupiter.api.Test;

class BankStatementProcessorTest {

  @Test
  void should_return_total_amount() {
    // given
    final var transactions = List.of(
        new BankTransaction(LocalDate.now(), 3000, "이천 원"),
        new BankTransaction(LocalDate.now(), 2000, "이천 원"),
        new BankTransaction(LocalDate.now(), 4000, "이천 원"),
        new BankTransaction(LocalDate.now().plusDays(2), 5000, "이천 원")
    );

    final var processor = new BankStatementProcessor(transactions);

    // when
    final var result = processor.calculateTotalAmount();
    final var expected = 14000;

    // then
    assertThat(result).isEqualTo(expected);
  }

  @Test
  void should_return_total_amount_in_month() {
    // given
    final var transactions = List.of(
        new BankTransaction(LocalDate.of(2022, 1, 20), 3000, "이천 원"),
        new BankTransaction(LocalDate.of(2022, 2, 20), 2000, "이천 원"),
        new BankTransaction(LocalDate.of(2022, 2, 20), 4000, "이천 원"),
        new BankTransaction(LocalDate.of(2022, 3, 20), 5000, "이천 원")
    );

    final var processor = new BankStatementProcessor(transactions);

    // when
    final var result = processor.calculateTotalInMonth(Month.FEBRUARY);
    final var expected = 6000;

    // then
    assertThat(result).isEqualTo(expected);
  }

  @Test
  void should_return_total_amount_for_category() {
    // given
    final var transactions = List.of(
        new BankTransaction(LocalDate.of(2022, 1, 20), 3000, "이천 원"),
        new BankTransaction(LocalDate.of(2022, 2, 20), 2000, "삼천 원"),
        new BankTransaction(LocalDate.of(2022, 2, 20), 4000, "사천 원"),
        new BankTransaction(LocalDate.of(2022, 3, 20), 5000, "삼천 원")
    );

    final var processor = new BankStatementProcessor(transactions);

    // when
    final var result = processor.calculateTotalForCategory("삼천 원");
    final var expected = 7000;

    // then
    assertThat(result).isEqualTo(expected);
  }

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

  @Test
  void should_return_filtered_transactions() {
    // given
    final var t1 = new BankTransaction(LocalDate.now(), 3000, "이천 원");
    final var t2 = new BankTransaction(LocalDate.now(), 4000, "삼천 원");
    final var t3 = new BankTransaction(LocalDate.now(), 2000, "삼천 원");
    final var t4 = new BankTransaction(LocalDate.now().plusDays(2), 1000, "사천 원");

    final var transactions = List.of(t1, t2, t3, t4);

    final var processor = new BankStatementProcessor(transactions);

    // when
    final var result1 = processor.findTransactions(t -> t.getAmount() >= 3000);
    final var expected1 = List.of(t1, t2);

    final var result2 = processor.findTransactions(t -> "삼천 원".equals(t.getDescription()));
    final var expected2 = List.of(t2, t3);

    // then
    assertThat(result1).isEqualTo(expected1);
    assertThat(result2).isEqualTo(expected2);
  }

  @Test
  void should_return_amount_greater_than_equal() {
    // given
    final var t1 = new BankTransaction(LocalDate.now(), 3000, "이천 원");
    final var t2 = new BankTransaction(LocalDate.now(), 4000, "삼천 원");
    final var t3 = new BankTransaction(LocalDate.now(), 2000, "삼천 원");
    final var t4 = new BankTransaction(LocalDate.now().plusDays(2), 1000, "사천 원");

    final var transactions = List.of(t1, t2, t3, t4);

    final var processor = new BankStatementProcessor(transactions);

    // when
    final var result1 = processor.findTransactionsGreaterThanEqual(3000);
    final var expected1 = List.of(t1, t2);

    final var result2 = processor.findTransactionsGreaterThanEqual(2000);
    final var expected2 = List.of(t1, t2, t3);

    // then
    assertThat(result1).isEqualTo(expected1);
    assertThat(result2).isEqualTo(expected2);
  }

}