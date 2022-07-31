import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;

public class MonthTotalCounter {

  private static final String RESOURCES = "src/main/resources/transaction/";

  public static void main(final String[] args) throws IOException {
    System.out.println("MonthTotalCounter.main");
    final var path = Paths.get(RESOURCES + args[0]);
    final var lines = Files.readAllLines(path);

    double total = 0d;
    final var DATE_PATTERN = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    for (final var line : lines) {
      final var columns = line.split(",");
      final var date = LocalDate.parse(columns[0], DATE_PATTERN);

      if (date.getMonth() == Month.JANUARY) {
        final double amount = Double.parseDouble(columns[1]);
        total += amount;
      }
    }

    System.out.println("total: " + total);
  }
}
