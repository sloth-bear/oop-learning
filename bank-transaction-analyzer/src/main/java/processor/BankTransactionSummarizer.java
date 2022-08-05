package processor;

import parser.BankTransaction;

@FunctionalInterface
public interface BankTransactionSummarizer {

  double summarize(double accumulator, BankTransaction bankTransaction);
}
