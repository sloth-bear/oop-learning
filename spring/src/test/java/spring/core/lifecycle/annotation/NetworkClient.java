package spring.core.lifecycle.annotation;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import spring.core.lifecycle.AbstractNetworkClient;

public class NetworkClient extends AbstractNetworkClient {

  @PostConstruct
  public void init() {
    connect();
    call("초기화 연결 메세지");
  }

  @PreDestroy
  public void close() {
    disconnect();
  }

}
