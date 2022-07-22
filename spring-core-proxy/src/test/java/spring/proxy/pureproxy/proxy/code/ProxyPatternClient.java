package spring.proxy.pureproxy.proxy.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProxyPatternClient {

  private final Subject subject;

  public ProxyPatternClient(final Subject subject) {
    this.subject = subject;
  }

  public void execute() {
    subject.operate();
  }
}
