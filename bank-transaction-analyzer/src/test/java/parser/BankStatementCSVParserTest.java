package parser;

import static org.assertj.core.api.Assertions.assertThat;

import domain.BankTransaction;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;

class BankStatementCSVParserTest {

  private static final DateTimeFormatter DATE_PATTERN = DateTimeFormatter.ofPattern("dd-MM-yyyy");
  private final BankStatementParser parser = new BankStatementCSVParser();

  @Test
  void should_return_empty_list_if_lines_are_empty() {
    // given
    final var lines = Collections.<String>emptyList();

    // when
    final var result = parser.parseLinesFrom(lines);
    final var expected = Collections.<BankTransaction>emptyList();

    // then
    assertThat(result).isEqualTo(expected);
  }

  @Test
  void should_parse_list_correct_lines() {
    // given
    final var lines = List.of("30-01-2022,-100000,Deliveroo", "02-02-2022,2000000,Royalties");

    // when
    final var result = parser.parseLinesFrom(lines);
    final var expected = List.of(
        new BankTransaction(
            LocalDate.parse("30-01-2022", DATE_PATTERN),
            -100000,
            "Deliveroo"
        ),
        new BankTransaction(
            LocalDate.parse("02-02-2022", DATE_PATTERN),
            2000000,
            "Royalties"
        )
    );

    // then
    assertThat(result).isEqualTo(expected);
  }

  @Test
  void should_parse_one_correct_line() {
    // given
    final var csvLine1 = "30-01-2022,-100000,Deliveroo";
    final var csvLine2 = "02-02-2022,2000000,Royalties";

    // when
    final var result1 = parser.parseFrom(csvLine1);
    final var result2 = parser.parseFrom(csvLine2);

    final var expected1 = new BankTransaction(
        LocalDate.parse("30-01-2022", DATE_PATTERN),
        -100000,
        "Deliveroo"
    );
    final var expected2 = new BankTransaction(
        LocalDate.parse("02-02-2022", DATE_PATTERN),
        2000000,
        "Royalties"
    );

    // then
    assertThat(result1).isEqualTo(expected1);
    assertThat(result2).isEqualTo(expected2);
  }

}