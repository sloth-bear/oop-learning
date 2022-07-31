import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Month;
import parser.BankStatementCSVParser;
import processor.BankStatementProcessor;

public class Main {

  private static final String RESOURCES = "src/main/resources/transaction/";

  public static void main(final String[] args) throws IOException {
    System.out.println("TotalCounter.main");
    final var path = Paths.get(RESOURCES + args[0]);
    final var lines = Files.readAllLines(path);

    final var parser = new BankStatementCSVParser();
    final var transactions = parser.parseLinesFromCSV(lines);
    final var processor = new BankStatementProcessor(transactions);

    System.out.println("total: " + processor.calculateTotalAmount());
    System.out.println(
        "transactions in january: " + processor.calculateTotalAmount(Month.JANUARY));
  }

}
