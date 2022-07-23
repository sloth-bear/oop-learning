package spring.proxy.pureproxy.decorator.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RealComponent implements Component {

  @Override
  public String operate() {
    log.info("RealComponent 실행");
    return "data";
  }
}
