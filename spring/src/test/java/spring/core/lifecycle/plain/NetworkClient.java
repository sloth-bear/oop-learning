package spring.core.lifecycle.plain;

import spring.core.lifecycle.AbstractNetworkClient;

public class NetworkClient extends AbstractNetworkClient {

  public NetworkClient() {
    super();
    connect();
    call("초기화 연결 메세지");
  }

}
