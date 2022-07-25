package spring.proxy.advisor;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import spring.proxy.common.ServiceImpl;
import spring.proxy.common.ServiceInterface;

public class MultiAdvisorTest {

  @Test
  @DisplayName("여러 프록시 테스트, 1개의 target - multiple advisor")
  void multiAdvisorTest1() {
    // client --> proxy2(advisor2) --> proxy1(advisor1) --> target
    final var target = new ServiceImpl();
    final var proxyFactory = new ProxyFactory(target);
    proxyFactory.addAdvisor(new DefaultPointcutAdvisor(Pointcut.TRUE, new Advice1()));

    final var proxyWrapTarget = (ServiceInterface) proxyFactory.getProxy();
    final var proxyFactory2 = new ProxyFactory(proxyWrapTarget);
    proxyFactory2.addAdvisor(new DefaultPointcutAdvisor(Pointcut.TRUE, new Advice2()));

    final var proxy = (ServiceInterface) proxyFactory2.getProxy();
    proxy.save();
    proxy.find();
  }

  @Test
  @DisplayName("여러 프록시 테스트 1에 비에 조금 더 나은 방법, 1개의 target - multiple advisor")
  void multiAdvisorTest2() {
    // client --> proxy --> advisor2 --> advisor1 --> target
    final var target = new ServiceImpl();
    final var proxyFactory = new ProxyFactory(target);
    proxyFactory.addAdvisor(new DefaultPointcutAdvisor(Pointcut.TRUE, new Advice2()));
    proxyFactory.addAdvisor(new DefaultPointcutAdvisor(Pointcut.TRUE, new Advice1()));

    final var proxy = (ServiceInterface) proxyFactory.getProxy();
    proxy.save();
    proxy.find();
  }

  @Slf4j
  static class Advice1 implements MethodInterceptor {

    @Override
    public Object invoke(final MethodInvocation invocation) throws Throwable {
      log.info("advice1 호출");
      return invocation.proceed();
    }
  }

  @Slf4j
  static class Advice2 implements MethodInterceptor {

    @Override
    public Object invoke(final MethodInvocation invocation) throws Throwable {
      log.info("advice2 호출");
      return invocation.proceed();
    }
  }
}
