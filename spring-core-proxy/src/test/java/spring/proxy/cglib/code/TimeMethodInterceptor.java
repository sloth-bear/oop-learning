package spring.proxy.cglib.code;

import java.lang.reflect.Method;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

@Slf4j
public class TimeMethodInterceptor implements MethodInterceptor {

  private final Object target;

  public TimeMethodInterceptor(final Object target) {
    this.target = target;
  }

  @Override
  public Object intercept(
      final Object o, final Method method, final Object[] args, final MethodProxy methodProxy)
      throws Throwable {
    log.info("TimeProxy execute");
    final var start = System.currentTimeMillis();
     
    final var result = methodProxy.invoke(target, args);

    final var end = System.currentTimeMillis();
    final var resultTime = end - start;
    log.info("TimeProxy end, resultTime = {}", resultTime);
    return result;
  }
}
