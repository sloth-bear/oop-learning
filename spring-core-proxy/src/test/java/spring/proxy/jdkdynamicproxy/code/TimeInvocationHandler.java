package spring.proxy.jdkdynamicproxy.code;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TimeInvocationHandler implements InvocationHandler {

  private final Object target;

  public TimeInvocationHandler(final Object target) {
    this.target = target;
  }

  @Override
  public Object invoke(final Object proxy, final Method method, final Object[] args)
      throws Throwable {
    log.info("TimeProxy execute");
    final var start = System.currentTimeMillis();

    final var result = method.invoke(target, args);

    final var end = System.currentTimeMillis();
    final var resultTime = end - start;
    log.info("TimeProxy end, resultTime = {}", resultTime);
    return result;
  }
}
