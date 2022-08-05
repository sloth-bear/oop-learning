package parser;

import domain.BankTransaction;
import exception.CSVSyntaxException;
import exception.InvalidBankStatementException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class BankStatementCSVParser implements BankStatementParser {

  private static final DateTimeFormatter DATE_PATTERN = DateTimeFormatter.ofPattern("dd-MM-yyyy");
  private static final int EXPECTED_ATTRIBUTES_LENGTH = 3;

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
    validate(columns);

    final var date = LocalDate.parse(columns[0], DATE_PATTERN);
    final var amount = Double.parseDouble(columns[1]);
    final var description = columns[2];

    return new BankTransaction(date, amount, description);
  }

  private void validate(final String[] columns) {
    if (columns.length < EXPECTED_ATTRIBUTES_LENGTH) {
      throw new CSVSyntaxException();
    }

    final var validator = new BankStatementValidator(columns[0], columns[1], columns[2]);
    final var result = validator.validate();

    if (result.hasErrors()) {
      result.getErrors().forEach(err -> {
        throw new InvalidBankStatementException(err);
      });
    }
  }

}
