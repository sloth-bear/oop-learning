package processor;

import domain.BankTransaction;

@FunctionalInterface
public interface BankTransactionFilter {

  boolean test(BankTransaction bankTransaction);
}
