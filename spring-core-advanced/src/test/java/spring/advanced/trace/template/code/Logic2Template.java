package spring.advanced.trace.template.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Logic2Template extends AbstractTemplate {

  @Override
  protected void call() {
    log.info("This is the business logic2");
  }

}
