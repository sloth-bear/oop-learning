package spring.advanced.trace.strategy.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Logic1Strategy implements Strategy {

  @Override
  public void call() {
    log.info("This is logic2 for business");
  }
}
