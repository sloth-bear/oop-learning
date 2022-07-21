package spring.advanced.trace.proto;

import org.junit.jupiter.api.Test;
import spring.advanced.trace.log.proto.ProtoLogTraceV1;

class ProtoLogTraceV1Test {

  @Test
  void trace() {
    final var trace = new ProtoLogTraceV1();
    final var status = trace.begin("Hello?");
    trace.end(status);
  }

  @Test
  void exception() {
    final var trace = new ProtoLogTraceV1();
    final var status = trace.begin("Hello?");
    trace.exception(status, new IllegalStateException());
  }
}