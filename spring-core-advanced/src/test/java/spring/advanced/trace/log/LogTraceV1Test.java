package spring.advanced.trace.log;

import org.junit.jupiter.api.Test;

class LogTraceV1Test {

  @Test
  void trace() {
    final var trace = new LogTraceV1();
    final var status = trace.begin("Hello?");
    trace.end(status);
  }

  @Test
  void exception() {
    final var trace = new LogTraceV1();
    final var status = trace.begin("Hello?");
    trace.exception(status, new IllegalStateException());
  }
}