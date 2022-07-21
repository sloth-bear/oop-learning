package spring.advanced.trace.strategy.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Context {

  private final Strategy strategy;

  public Context(final Strategy strategy) {
    this.strategy = strategy;
  }

  public void execute() {
    final var startTime = System.currentTimeMillis();

    strategy.call();

    final var endTime = System.currentTimeMillis();
    final var result = endTime - startTime;
    log.info(String.format("result: {}%d", result));
  }
}
