package spring.advanced.trace.template.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractTemplate {

  public void execute() {
    final var startTime = System.currentTimeMillis();

    call();

    final var endTime = System.currentTimeMillis();
    final var result = endTime - startTime;
    log.info(String.format("result: {}%d", result));
  }

  protected abstract void call();

}
