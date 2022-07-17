package spring.core.lifecycle.implementation;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import spring.core.lifecycle.AbstractNetworkClient;

public class NetworkClient extends AbstractNetworkClient implements InitializingBean,
    DisposableBean {

  @Override
  public void afterPropertiesSet() {
    connect();
    call("초기화 연결 메세지");
  }

  @Override
  public void destroy() {
    disconnect();
  }
}
