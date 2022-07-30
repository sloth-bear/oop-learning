package spring.core.aop.pointcut;

import static org.assertj.core.api.Assertions.assertThat;

import java.lang.reflect.Method;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import spring.core.aop.member.MemberServiceImpl;

@Slf4j
public class TypeMatchExecutionTest {

  AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
  Method greetMethod;

  @BeforeEach
  public void init() throws NoSuchMethodException {
    greetMethod = MemberServiceImpl.class.getMethod("externalGreet", String.class);
  }

  @Test
  void printMethod() {
    // public java.lang.String spring.core.aop.member.MemberServiceImpl.externalGreet(java.lang.String)
    log.info("greet method = {}", greetMethod);
  }

  @Test
  void typeExactMatch() {
    final var typeExact = "execution(* spring.core.aop.member.MemberServiceImpl.externalGreet(..))";
    pointcut.setExpression(typeExact);

    assertThat(pointcut.matches(greetMethod, MemberServiceImpl.class)).isTrue();
  }

  @Test
  void superTypeMatch() {
    final var superType = "execution(* spring.core.aop.member.MemberService.*(..))";
    pointcut.setExpression(superType);

    assertThat(pointcut.matches(greetMethod, MemberServiceImpl.class)).isTrue();
  }

  @Test
  void subTypeInternalMethodIsNotMatch() throws NoSuchMethodException {
    final var superType = "execution(* spring.core.aop.member.MemberService.*(..))";
    pointcut.setExpression(superType);

    final var internalMethod = MemberServiceImpl.class.getMethod("internalGreet", String.class);
    assertThat(pointcut.matches(internalMethod, MemberServiceImpl.class)).isFalse();
  }

  @Test
  void typeMatchInternal() throws NoSuchMethodException {
    final var superType = "execution(* spring.core.aop.member.MemberServiceImpl.*(..))";
    pointcut.setExpression(superType);

    final var internalMethod = MemberServiceImpl.class.getMethod("internalGreet", String.class);
    assertThat(pointcut.matches(internalMethod, MemberServiceImpl.class)).isTrue();
  }
}
