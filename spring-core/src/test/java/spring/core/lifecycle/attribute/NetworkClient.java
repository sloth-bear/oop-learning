package spring.core.lifecycle.attribute;

import spring.core.lifecycle.AbstractNetworkClient;

public class NetworkClient extends AbstractNetworkClient {

  public void init() {
    connect();
    call("초기화 연결 메세지");
  }

  public void close() {
    disconnect();
  }

}
