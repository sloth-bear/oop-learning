package parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class BankStatementCSVParser implements BankStatementParser {

  private static final String RESOURCES = "src/main/resources/transaction/";
  private static final DateTimeFormatter DATE_PATTERN = DateTimeFormatter.ofPattern("dd-MM-yyyy");

  @Override
  public List<BankTransaction> parseLinesFrom(final List<String> lines) {
    final var result = new ArrayList<BankTransaction>();

    for (final var line : lines) {
      result.add(parseFrom(line));
    }

    return result;
  }

  @Override
  public BankTransaction parseFrom(final String line) {
    final var columns = line.split(",");

    final var date = LocalDate.parse(columns[0], DATE_PATTERN);
    final var amount = Double.parseDouble(columns[1]);
    final var description = columns[2];

    return new BankTransaction(date, amount, description);
  }

}
