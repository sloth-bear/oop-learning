package analyzer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Month;
import parser.BankStatementParser;
import processor.BankStatementProcessor;

public class BankStatementAnalyzer {

  private static final String RESOURCES = "src/main/resources/transaction/";

  public void analyze(final String fileName, final BankStatementParser parser)
      throws IOException {
    final var path = Paths.get(RESOURCES + fileName);
    final var lines = Files.readAllLines(path);

    final var transactions = parser.parseLinesFrom(lines);
    final var processor = new BankStatementProcessor(transactions);

    collectSummary(processor);
  }

  private static void collectSummary(final BankStatementProcessor processor) {
    System.out.println("total: " + processor.calculateTotalAmount());
    System.out.println(
        "total in january: " + processor.calculateTotalInMonth(Month.JANUARY));
    System.out.println(
        "total in february: " + processor.calculateTotalInMonth(Month.FEBRUARY));
    System.out.println(
        "total in category: " + processor.calculateTotalForCategory("엽떡"));
  }

}
