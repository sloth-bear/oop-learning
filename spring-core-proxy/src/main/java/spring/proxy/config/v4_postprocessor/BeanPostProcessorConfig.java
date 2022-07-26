package spring.proxy.config.v4_postprocessor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.Advisor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import spring.proxy.config.AppV1Config;
import spring.proxy.config.AppV2Config;
import spring.proxy.config.v3_factory.advice.LogTraceAdvice;
import spring.proxy.trace.logtrace.LogTrace;

@Slf4j
@Configuration
@Import({AppV1Config.class, AppV2Config.class})
public class BeanPostProcessorConfig {

  @Bean
  public PackageLogTracePostProcessor logTracePostProcessor(final LogTrace logTrace) {
    return new PackageLogTracePostProcessor("spring.proxy.app", getAdvisor(logTrace));
  }

  private Advisor getAdvisor(final LogTrace logTrace) {
    final var pointcut = new NameMatchMethodPointcut();
    pointcut.setMappedNames("orders*", "save*");

    return new DefaultPointcutAdvisor(pointcut, new LogTraceAdvice(logTrace));
  }
}
