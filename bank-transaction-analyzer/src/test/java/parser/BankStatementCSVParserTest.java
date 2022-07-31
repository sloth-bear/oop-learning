package parser;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.Test;

class BankStatementCSVParserTest {

  private final BankStatementParser parser = new BankStatementCSVParser();

  @Test
  void should_parse_one_correct_line() {
    // given
    final var csvLine1 = "30-01-2022,-100000,Deliveroo";
    final var csvLine2 = "02-02-2022,2000000,Royalties";
    final var datePattern = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    // when
    final var result1 = parser.parseFrom(csvLine1);
    final var result2 = parser.parseFrom(csvLine2);

    final var expected1 = new BankTransaction(
        LocalDate.parse("30-01-2022", datePattern),
        -100000,
        "Deliveroo"
    );
    final var expected2 = new BankTransaction(
        LocalDate.parse("02-02-2022", datePattern),
        2000000,
        "Royalties"
    );

    // then
    assertThat(result1).isEqualTo(expected1);
    assertThat(result2).isEqualTo(expected2);
  }

}