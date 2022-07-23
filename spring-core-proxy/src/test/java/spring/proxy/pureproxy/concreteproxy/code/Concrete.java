package spring.proxy.pureproxy.concreteproxy.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Concrete {

  public String operate() {
    log.info("Concrete operate!");
    return "data";
  }
}
