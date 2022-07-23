package spring.proxy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.proxy.trace.logtrace.LogTrace;
import spring.proxy.trace.logtrace.ThreadLocalLogTrace;

@Configuration
public class LogTraceConfig {

  @Bean
  public LogTrace logTrace() {
    return new ThreadLocalLogTrace();
  }
}
