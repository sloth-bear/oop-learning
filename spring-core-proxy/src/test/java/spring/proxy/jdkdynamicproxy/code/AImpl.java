package spring.proxy.jdkdynamicproxy.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AImpl implements AInterface {

  @Override
  public String call() {
    log.info("call A");
    return "A";
  }
}
