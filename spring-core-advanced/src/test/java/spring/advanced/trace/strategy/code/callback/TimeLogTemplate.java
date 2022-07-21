package spring.advanced.trace.strategy.code.callback;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TimeLogTemplate {

  public void execute(final Callback callback) {
    final var startTime = System.currentTimeMillis();

    callback.call();

    final var endTime = System.currentTimeMillis();
    final var result = endTime - startTime;
    log.info(String.format("result: {}%d", result));
  }

}
