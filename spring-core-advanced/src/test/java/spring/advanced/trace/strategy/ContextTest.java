package spring.advanced.trace.strategy;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import spring.advanced.trace.strategy.code.strategy.Context;
import spring.advanced.trace.strategy.code.strategy.Logic1Strategy;
import spring.advanced.trace.strategy.code.strategy.Logic2Strategy;

@Slf4j
public class ContextTest {

  @Test
  void contextV0() {
    logic1();
    logic2();
  }

  @Test
  void contextV1() {
    final var context1 = new Context(new Logic1Strategy());
    context1.execute();

    final var context2 = new Context(new Logic2Strategy());
    context2.execute();
  }

  @Test
  void contextV2() {
    final var context1 = new Context(() -> log.info("logic1"));
    context1.execute();

    final var context2 = new Context(() -> log.info("logic2"));
    context2.execute();
  }

  private void logic1() {
    final var startTime = System.currentTimeMillis();

    log.info("This is the business logic1");

    final var endTime = System.currentTimeMillis();
    final var result = endTime - startTime;
    log.info(String.format("result: {}%d", result));
  }

  private void logic2() {
    final var startTime = System.currentTimeMillis();

    log.info("This is the business logic2");

    final var endTime = System.currentTimeMillis();
    final var result = endTime - startTime;
    log.info(String.format("result: {}%d", result));
  }

}
