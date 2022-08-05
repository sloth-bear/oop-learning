package processor;

import parser.BankTransaction;

@FunctionalInterface
public interface BankTransactionFilter {

  boolean test(BankTransaction bankTransaction);
}
