package spring.advanced.trace.strategy.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DynamicContext {

  public void execute(final Strategy strategy) {
    final var startTime = System.currentTimeMillis();

    strategy.call();

    final var endTime = System.currentTimeMillis();
    final var result = endTime - startTime;
    log.info(String.format("result: {}%d", result));
  }
}
