package spring.proxy.config.v5_autoproxy;

import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import spring.proxy.config.AppV1Config;
import spring.proxy.config.AppV2Config;
import spring.proxy.config.v3_factory.advice.LogTraceAdvice;
import spring.proxy.trace.logtrace.LogTrace;

@Configuration
@Import({AppV1Config.class, AppV2Config.class})
public class AutoProxyConfig {

  //  @Bean
  public Advisor advisor1(final LogTrace logTrace) {
    // pointcut
    final var pointcut = new NameMatchMethodPointcut();
    pointcut.setMappedNames("orders*", "save*");

    // advice
    return new DefaultPointcutAdvisor(pointcut, new LogTraceAdvice(logTrace));
  }

  //  @Bean
  public Advisor advisor2(final LogTrace logTrace) {
    // pointcut
    final var pointcut = new AspectJExpressionPointcut();
    pointcut.setExpression("execution(* spring.proxy.app..*(..))");

    // advice
    return new DefaultPointcutAdvisor(pointcut, new LogTraceAdvice(logTrace));
  }

  @Bean
  public Advisor advisor3(final LogTrace logTrace) {
    // pointcut
    final var pointcut = new AspectJExpressionPointcut();
    pointcut.setExpression(
        "execution(* spring.proxy.app..*(..)) && !execution(* spring.proxy.app..noLogs(..))");

    // advice
    return new DefaultPointcutAdvisor(pointcut, new LogTraceAdvice(logTrace));
  }

}
