package spring.advanced.trace.strategy;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import spring.advanced.trace.strategy.code.DynamicContext;
import spring.advanced.trace.strategy.code.Logic1Strategy;
import spring.advanced.trace.strategy.code.Logic2Strategy;
import spring.advanced.trace.strategy.code.Strategy;

@Slf4j
public class DynamicStrategyTest {

  @Test
  void test() {
    final var context = new DynamicContext();
    context.execute(new Logic1Strategy());
    context.execute(new Logic2Strategy());
  }

  @Test
  @SuppressWarnings("Convert2Lambda")
  void anonymous() {
    final var context = new DynamicContext();
    context.execute(() -> new Strategy() {
      @Override
      public void call() {
        log.info("logic1");
      }
    });
    context.execute(() -> new Strategy() {
      @Override
      public void call() {
        log.info("logic2");
      }
    });
  }

  @Test
  void lambda() {
    final var context = new DynamicContext();
    context.execute(() -> log.info("logic1"));
    context.execute(() -> log.info("logic2"));
  }

}
