package parser;

import domain.Notification;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class BankStatementValidator {

  private static final DateTimeFormatter DATE_PATTERN = DateTimeFormatter.ofPattern("dd-MM-yyyy");

  private final String date;
  private final String amount;
  private final String description;

  public BankStatementValidator(final String date, final String amount, final String description) {
    this.date = date;
    this.amount = amount;
    this.description = description;
  }

  public Notification validate() {
    final var notification = new Notification();

    if (this.description.length() > 100) {
      notification.addError("The description is too long");
    }

    final LocalDate parsedDate;
    try {
      parsedDate = LocalDate.parse(this.date, DATE_PATTERN);
      if (parsedDate.isAfter(LocalDate.now())) {
        notification.addError("The date cannot be in the future");
      }
    } catch (final DateTimeParseException e) {
      notification.addError("Invalid format for date");
    }

    try {
      Double.parseDouble(this.amount);
    } catch (final NumberFormatException e) {
      notification.addError("Invalid format for amount");
    }

    return notification;
  }
}
