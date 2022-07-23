package spring.proxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import spring.proxy.config.AppV1ProxyConfig;
import spring.proxy.config.LogTraceConfig;

//@Import({AppV1Config.class, AppV2Config.class})
@Import({AppV1ProxyConfig.class, LogTraceConfig.class})
@SpringBootApplication(scanBasePackages = "spring.proxy.app") //주의
public class ProxyApplication {

  public static void main(final String[] args) {
    SpringApplication.run(ProxyApplication.class, args);
  }

}
