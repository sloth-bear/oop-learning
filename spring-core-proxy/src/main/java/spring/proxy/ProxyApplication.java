package spring.proxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import spring.proxy.config.LogTraceConfig;
import spring.proxy.config.proxy.ConcreteProxyConfig;
import spring.proxy.config.proxy.InterfaceProxyConfig;

//@Import({AppV1Config.class, AppV2Config.class})
@Import({ConcreteProxyConfig.class, InterfaceProxyConfig.class, LogTraceConfig.class})
@SpringBootApplication(scanBasePackages = "spring.proxy.app") //주의
public class ProxyApplication {

  public static void main(final String[] args) {
    SpringApplication.run(ProxyApplication.class, args);
  }

}