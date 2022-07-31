import analyzer.BankStatementAnalyzer;
import java.io.IOException;
import parser.BankStatementCSVParser;

public class Main {

  public static void main(final String[] args) throws IOException {
    System.out.println("TotalCounter.main");
    final var bankStatementAnalyzer = new BankStatementAnalyzer();
    bankStatementAnalyzer.analyze(args[0], new BankStatementCSVParser());
  }

}
