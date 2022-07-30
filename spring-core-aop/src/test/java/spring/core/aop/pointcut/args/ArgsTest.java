package spring.core.aop.pointcut.args;

import static org.assertj.core.api.Assertions.assertThat;

import java.lang.reflect.Method;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import spring.core.aop.member.MemberServiceImpl;

public class ArgsTest {

  Method greetMethod;

  @BeforeEach
  public void init() throws NoSuchMethodException {
    greetMethod = MemberServiceImpl.class.getMethod("externalGreet", String.class);
  }

  private AspectJExpressionPointcut pointcut(final String expression) {
    final AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
    pointcut.setExpression(expression);
    return pointcut;
  }

  @Test
  void argsMatch() {
    assertThat(pointcut("args(String)")
        .matches(greetMethod, MemberServiceImpl.class)).isTrue();
    assertThat(pointcut("args(Object)")
        .matches(greetMethod, MemberServiceImpl.class)).isTrue();
    assertThat(pointcut("args()")
        .matches(greetMethod, MemberServiceImpl.class)).isFalse();
    assertThat(pointcut("args(..)")
        .matches(greetMethod, MemberServiceImpl.class)).isTrue();
    assertThat(pointcut("args(*)")
        .matches(greetMethod, MemberServiceImpl.class)).isTrue();
    assertThat(pointcut("args(String, ..)")
        .matches(greetMethod, MemberServiceImpl.class)).isTrue();
  }

  /**
   * execution: 메서드의 시그니처로 판단(정적) args: 런타임에 전달된 인수로 판단(동적)
   */
  @Test
  @DisplayName("execution: 파라미터에 상위 타입을 허용하지 않는다.")
  void argsVSExecution() {
    assertThat(pointcut("args(String)")
        .matches(greetMethod, MemberServiceImpl.class)).isTrue();
    assertThat(pointcut("args(java.io.Serializable)")
        .matches(greetMethod, MemberServiceImpl.class)).isTrue();
    assertThat(pointcut("args(Object)")
        .matches(greetMethod, MemberServiceImpl.class)).isTrue();

    assertThat(pointcut("execution(* *(String))")
        .matches(greetMethod, MemberServiceImpl.class)).isTrue();
    assertThat(pointcut("execution(* *(java.io.Serializable))")
        .matches(greetMethod, MemberServiceImpl.class)).isFalse();
    assertThat(pointcut("execution(* *(Object))")
        .matches(greetMethod, MemberServiceImpl.class)).isFalse();
  }

}
