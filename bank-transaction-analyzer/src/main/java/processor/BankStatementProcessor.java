package processor;

import java.time.Month;
import java.util.List;
import java.util.stream.Collectors;
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

  public List<BankTransaction> calculateTotalAmount(
      final Month month) {
    return transactions.stream()
        .filter(t -> t.getDate().getMonth() == month)
        .collect(Collectors.toList());
  }

}
