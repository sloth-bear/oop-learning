package processor;

import java.time.Month;
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
}
