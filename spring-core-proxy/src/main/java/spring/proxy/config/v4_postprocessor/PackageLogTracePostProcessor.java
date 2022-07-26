package spring.proxy.config.v4_postprocessor;

import static org.springframework.util.StringUtils.startsWithIgnoreCase;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

@Slf4j
public class PackageLogTracePostProcessor implements BeanPostProcessor {

  private final String basePackage;
  private final Advisor advisor;

  public PackageLogTracePostProcessor(final String basePackage, final Advisor advisor) {
    this.basePackage = basePackage;
    this.advisor = advisor;
  }

  @Override
  public Object postProcessAfterInitialization(final Object bean, final String beanName)
      throws BeansException {
    log.info("beanName:{}, bean:{}", beanName, bean.getClass());

    if (!containsTargetPackage(bean.getClass().getPackageName())) {
      return bean;
    }

    final var factory = new ProxyFactory(bean);
    factory.addAdvisor(advisor);

    final var proxy = factory.getProxy();
    log.info("create proxy: target={}, proxy={}", bean.getClass(), proxy.getClass());
    return proxy;
  }

  private boolean containsTargetPackage(final String packageName) {
    return startsWithIgnoreCase(packageName, basePackage);
  }

}
