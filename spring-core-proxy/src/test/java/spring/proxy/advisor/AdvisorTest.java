package spring.proxy.advisor;

import java.lang.reflect.Method;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import spring.proxy.common.ServiceImpl;
import spring.proxy.common.ServiceInterface;
import spring.proxy.common.advice.TimeAdvice;

@Slf4j
public class AdvisorTest {

  @Test
  void advisorTest1() {
    final var target = new ServiceImpl();
    final var proxyFactory = new ProxyFactory(target);
    proxyFactory.addAdvisor(new DefaultPointcutAdvisor(Pointcut.TRUE, new TimeAdvice()));
    final var proxy = (ServiceInterface) proxyFactory.getProxy();

    proxy.save();
    proxy.find();
  }

  @Test
  @DisplayName("포인트컷 직접 생성")
  void advisorTest2() {
    final var target = new ServiceImpl();
    final var proxyFactory = new ProxyFactory(target);
    proxyFactory.addAdvisor(new DefaultPointcutAdvisor(new MyPointcut(), new TimeAdvice()));
    final var proxy = (ServiceInterface) proxyFactory.getProxy();

    proxy.save();
    proxy.find();
  }

  @Test
  @DisplayName("스프링 제공 포인트컷")
  void advisorTest3() {
    final var target = new ServiceImpl();
    final var proxyFactory = new ProxyFactory(target);

    final var pointcut = new NameMatchMethodPointcut();
    pointcut.setMappedName("save");

    proxyFactory.addAdvisor(new DefaultPointcutAdvisor(pointcut, new TimeAdvice()));
    final var proxy = (ServiceInterface) proxyFactory.getProxy();

    proxy.save();
    proxy.find();
  }

  static class MyPointcut implements Pointcut {

    @Override
    public ClassFilter getClassFilter() {
      return ClassFilter.TRUE;
    }

    @Override
    public MethodMatcher getMethodMatcher() {
      return new MyMethodMatcher();
    }
  }

  @Slf4j
  static class MyMethodMatcher implements MethodMatcher {

    private static final String FILTER_METHOD_NAME = "save";

    @Override
    public boolean matches(final Method method, final Class<?> targetClass) {
      final boolean result = FILTER_METHOD_NAME.equals(method.getName());

      log.info("포인트컷 호출 method: {}, targetClass: {}", method.getName(), targetClass);
      log.info("포인트컷 결과 result: {}", result);

      return result;
    }

    @Override
    public boolean isRuntime() {
      return false;
    }

    @Override
    public boolean matches(final Method method, final Class<?> targetClass, final Object... args) {
      return false;
    }
  }
}
