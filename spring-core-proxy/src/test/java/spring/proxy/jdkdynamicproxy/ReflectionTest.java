package spring.proxy.jdkdynamicproxy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ReflectionTest {

  @Test
  void noReflection() {
    final var target = new HelloReflection();

    log.info("callA() start");
    final var result1 = target.callA();
    log.info("callA() result={}", result1);

    log.info("callB() start");
    final var result2 = target.callB();
    log.info("callB() result={}", result2);
  }

  @Test
  void reflection1()
      throws NoSuchMethodException, ClassNotFoundException, InvocationTargetException, IllegalAccessException {
    final var className = Class.forName(
        "spring.proxy.jdkdynamicproxy.ReflectionTest$HelloReflection");

    final var target = new HelloReflection();
    final var methodCallA = className.getMethod("callA");
    final var result1 = methodCallA.invoke(target);
    log.info("callA() result={}", result1);

    final var methodCallB = className.getMethod("callB");
    final var result2 = methodCallB.invoke(target);
    log.info("callB() result={}", result2);
  }

  @Test
  void reflection2()
      throws NoSuchMethodException, ClassNotFoundException, InvocationTargetException, IllegalAccessException {
    final var classMetadata = Class.forName(
        "spring.proxy.jdkdynamicproxy.ReflectionTest$HelloReflection");
    final var targetInstance = new HelloReflection();

    callDynamic(classMetadata.getMethod("callA"), targetInstance);
    callDynamic(classMetadata.getMethod("callB"), targetInstance);
  }

  private void callDynamic(final Method method, final Object target)
      throws IllegalAccessException, InvocationTargetException {
    log.info("start");
    final var result = method.invoke(target);
    log.info("result={}", result);
  }

  @Slf4j
  static class HelloReflection {

    public String callA() {
      log.info("callA");
      return "A";
    }

    public String callB() {
      log.info("callB");
      return "B";
    }
  }
}
