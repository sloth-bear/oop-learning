package spring.proxy.common;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ServiceConcrete {

  public void call() {
    log.info("ServiceConcrete.call()");
  }
}
