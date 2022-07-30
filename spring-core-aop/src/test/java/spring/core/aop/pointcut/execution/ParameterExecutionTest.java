package spring.core.aop.pointcut.execution;

import static org.assertj.core.api.Assertions.assertThat;

import java.lang.reflect.Method;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import spring.core.aop.member.MemberServiceImpl;

public class ParameterExecutionTest {

  AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
  Method greetMethod;

  @BeforeEach
  public void init() throws NoSuchMethodException {
    greetMethod = MemberServiceImpl.class.getMethod("externalGreet", String.class);
  }

  @Test
  void argsMatch() {
    final var superType = "execution(* *(String))";
    pointcut.setExpression(superType);

    assertThat(pointcut.matches(greetMethod, MemberServiceImpl.class)).isTrue();
  }

  @Test
  @DisplayName("파라미터가 없는 경우 --> ()")
  void noArgsMatch() {
    final var superType = "execution(* *())";
    pointcut.setExpression(superType);

    assertThat(pointcut.matches(greetMethod, MemberServiceImpl.class)).isFalse();
  }

  @Test
  @DisplayName("정확히 하나의 파라미터만 존재하여야 할 경우 --> (xxx)")
  void oneArgsMatch() {
    final var superType = "execution(* *(*))";
    pointcut.setExpression(superType);

    assertThat(pointcut.matches(greetMethod, MemberServiceImpl.class)).isTrue();
  }

  @Test
  @DisplayName("파라미터 개수 무관, 모든 타입 허용 --> (), (xxx), (xxx, xxx)")
  void allArgsMatch() {
    final var superType = "execution(* *(..))";
    pointcut.setExpression(superType);

    assertThat(pointcut.matches(greetMethod, MemberServiceImpl.class)).isTrue();
  }

  @Test
  @DisplayName("파라미터 개수 2개, String 타입으로 시작 --> (String), (String, xxx)")
  void complexArgsMatch1() {
    final var superType = "execution(* *(String, *))";
    pointcut.setExpression(superType);

    assertThat(pointcut.matches(greetMethod, MemberServiceImpl.class)).isFalse();
  }

  @Test
  @DisplayName("파라미터 개수 무관, String 타입으로 시작 --> (String), (String, xxx), (String, xxx, xxx)")
  void complexArgsMatch2() {
    final var superType = "execution(* *(String, ..))";
    pointcut.setExpression(superType);

    assertThat(pointcut.matches(greetMethod, MemberServiceImpl.class)).isTrue();
  }
}
