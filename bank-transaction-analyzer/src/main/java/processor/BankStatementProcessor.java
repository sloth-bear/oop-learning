package processor;

import java.time.LocalDate;
import java.time.Month;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import parser.BankTransaction;

public class BankStatementProcessor {

  private final List<BankTransaction> transactions;

  public BankStatementProcessor(final List<BankTransaction> transactions) {
    this.transactions = transactions;
  }

  public double calculateTotalAmount() {
    return transactions.stream()
        .mapToDouble(BankTransaction::getAmount)
        .sum();
  }

  public double calculateTotalInMonth(
      final Month month) {
    return calculateTotalAmount(t -> t.getDate().getMonth() == month);
  }

  public double calculateTotalForCategory(final String category) {
    return calculateTotalAmount(t -> Objects.equals(category, t.getDescription()));
  }

  public double calculateTotalAmount(final Predicate<BankTransaction> predicate) {
    return transactions.stream()
        .filter(predicate)
        .mapToDouble(BankTransaction::getAmount)
        .sum();
  }

  public BankTransaction findMaxAmount(final LocalDate startDate, final LocalDate endDate) {
    return transactions.stream()
        .filter(t -> t.getDate().isEqual(startDate) || t.getDate().isAfter(startDate))
        .filter(t -> t.getDate().isEqual(endDate) || t.getDate().isBefore(endDate))
        .max(Comparator.comparing(BankTransaction::getAmount))
        .orElseThrow(IllegalStateException::new);
  }

  public BankTransaction findMinAmount(final LocalDate startDate, final LocalDate endDate) {
    return transactions.stream()
        .filter(t -> t.getDate().isEqual(startDate) || t.getDate().isAfter(startDate))
        .filter(t -> t.getDate().isEqual(endDate) || t.getDate().isBefore(endDate))
        .min(Comparator.comparing(BankTransaction::getAmount))
        .orElseThrow(IllegalStateException::new);
  }

}
