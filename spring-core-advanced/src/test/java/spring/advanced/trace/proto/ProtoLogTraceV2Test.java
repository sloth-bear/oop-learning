package spring.advanced.trace.proto;

import org.junit.jupiter.api.Test;

public class ProtoLogTraceV2Test {

  @Test
  void trace() {
    final var trace = new ProtoLogTraceV2();
    final var status = trace.begin("Hello?");
    trace.end(status);
  }


  @Test
  void traceWithPrev() {
    final var trace = new ProtoLogTraceV2();
    final var firstStatus = trace.begin("Hello?");
    final var secondStatus = trace.begin(firstStatus.getTraceId(), "hello2?");

    trace.end(secondStatus);
    trace.end(firstStatus);
  }

  @Test
  void exception() {
    final var trace = new ProtoLogTraceV2();
    final var status = trace.begin("Hello?");
    trace.exception(status, new IllegalStateException());
  }

  @Test
  void beginSyncWithException() {
    final var trace = new ProtoLogTraceV2();
    final var firstStatus = trace.begin("Hello?");
    final var secondStatus = trace.begin(firstStatus.getTraceId(), "hello2?");

    trace.exception(secondStatus, new IllegalStateException());
    trace.exception(firstStatus, new IllegalStateException());
  }
}
