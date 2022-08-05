package exporter;

import domain.SummaryStatistics;

public class HtmlExporter implements Exporter {

  @Override
  public String export(final SummaryStatistics summaryStatistics) {
    String result = "<!DOCTYPE html>";
    result += "<html lang=\"en\">";
    result += "<head><title>Document</title></head>";
    result += "<body>";
    result += "<ul>";
    result += "  <li><strong>The sum is</strong>:" + summaryStatistics.getSum() + "</li>";
    result += "  <li><strong>The average is</strong>:" + summaryStatistics.getAverage() + "</li>";
    result += "  <li><strong>The max is</strong>:" + summaryStatistics.getMax() + "</li>";
    result += "  <li><strong>The min is</strong>:" + summaryStatistics.getMin() + "</li>";
    result += "</ul>";
    result += "</body>";
    result += "</html>";
    return result;
  }
}
