package processor;

import static java.util.stream.Collectors.toList;

import domain.BankTransaction;
import java.time.LocalDate;
import java.time.Month;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

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

  public double summarizeTransactions(final BankTransactionSummarizer summarizer) {
    double result = 0;
    for (final var transaction : transactions) {
      result = summarizer.summarize(result, transaction);
    }
    return result;
  }

  public double calculateTotalInMonth(final Month month) {
    return summarizeTransactions((acc, transaction) ->
        transaction.getDate().getMonth() == month ? acc + transaction.getAmount() : acc
    );
  }

  public double calculateTotalForCategory(final String category) {
    return summarizeTransactions((acc, transaction) ->
        Objects.equals(category, transaction.getDescription()) ? acc + transaction.getAmount() : acc
    );
  }

  public BankTransaction findMaxAmount(final LocalDate startDate, final LocalDate endDate) {
    return transactions.stream()
        .filter(t -> t.getDate().compareTo(startDate) >= 0)
        .filter(t -> t.getDate().compareTo(endDate) <= 0)
        .max(Comparator.comparing(BankTransaction::getAmount))
        .orElseThrow(IllegalStateException::new);
  }

  public BankTransaction findMinAmount(final LocalDate startDate, final LocalDate endDate) {
    return transactions.stream()
        .filter(t -> t.getDate().compareTo(startDate) >= 0)
        .filter(t -> t.getDate().compareTo(endDate) <= 0)
        .min(Comparator.comparing(BankTransaction::getAmount))
        .orElseThrow(IllegalStateException::new);
  }

  public List<BankTransaction> findTransactions(final BankTransactionFilter filter) {
    return transactions.stream().filter(filter::test).collect(toList());
  }

  public List<BankTransaction> findTransactionsGreaterThanEqual(final int amount) {
    return findTransactions(t -> t.getAmount() >= amount);
  }

}
