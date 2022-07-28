package spring.proxy.config.v6_aop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import spring.proxy.config.AppV1Config;
import spring.proxy.config.AppV2Config;
import spring.proxy.config.v6_aop.aspect.LogTraceAspect;
import spring.proxy.trace.logtrace.LogTrace;

@Configuration
@Import({AppV1Config.class, AppV2Config.class})
public class AopConfig {

  @Bean
  public LogTraceAspect logTraceAspect(final LogTrace logTrace) {
    return new LogTraceAspect(logTrace);
  }
}
