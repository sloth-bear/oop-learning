package spring.advanced.trace.log;

import org.junit.jupiter.api.Test;

class FieldLogTraceTest {

  FieldLogTrace trace = new FieldLogTrace();

  @Test
  void traceTest() {
    final var firstStatus = trace.begin("first logic");
    final var secondStatus = trace.begin("second logic");

    trace.end(secondStatus);
    trace.end(firstStatus);
  }

  @Test
  void exceptionTest() {
    final var firstStatus = trace.begin("first logic");
    final var secondStatus = trace.begin("second logic");

    trace.exception(secondStatus, new IllegalStateException());
    trace.exception(firstStatus, new IllegalStateException());
  }

}