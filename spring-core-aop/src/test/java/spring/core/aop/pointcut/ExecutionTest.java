package spring.core.aop.pointcut;

import static org.assertj.core.api.Assertions.assertThat;

import java.lang.reflect.Method;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import spring.core.aop.member.MemberServiceImpl;

@Slf4j
public class ExecutionTest {

  AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
  Method greetMethod;

  @BeforeEach
  public void init() throws NoSuchMethodException {
    greetMethod = MemberServiceImpl.class.getMethod("externalGreet", String.class);
  }

  @Test
  void printMethod() {
    log.info("greet method = {}", greetMethod);
  }

  @Test
  void noMatch() {
    final var notMatchedExpression = "execution(* *nono*(..))";
    pointcut.setExpression(notMatchedExpression);

    assertThat(pointcut.matches(greetMethod, MemberServiceImpl.class)).isFalse();
  }

  @Test
  void exactMatch() {
    // public java.lang.String spring.core.aop.member.MemberServiceImpl.externalGreet(java.lang.String)
    final var exactExpression = "execution(public String spring.core.aop.member.MemberServiceImpl.externalGreet(String))";
    pointcut.setExpression(exactExpression);

    assertThat(pointcut.matches(greetMethod, MemberServiceImpl.class)).isTrue();
  }

  @Test
  void requiredExpression() {
    // public java.lang.String spring.core.aop.member.MemberServiceImpl.externalGreet(java.lang.String)
    final var requiredExpression = "execution(String externalGreet(String))";
    pointcut.setExpression(requiredExpression);

    assertThat(pointcut.matches(greetMethod, MemberServiceImpl.class)).isTrue();
  }

  @Test
  void allMatch() {
    // public java.lang.String spring.core.aop.member.MemberServiceImpl.externalGreet(java.lang.String)
    final var allMatchExpression = "execution(* *(..))";
    pointcut.setExpression(allMatchExpression);

    assertThat(pointcut.matches(greetMethod, MemberServiceImpl.class)).isTrue();
  }

  @Test
  void nameMatch() {
    final var nameMatchExpression = "execution(* externalGreet(..))";
    pointcut.setExpression(nameMatchExpression);

    assertThat(pointcut.matches(greetMethod, MemberServiceImpl.class)).isTrue();
  }

  @Test
  void patternMatchStar1() {
    final var patternMatchExpression = "execution(* external*(..))";
    pointcut.setExpression(patternMatchExpression);

    assertThat(pointcut.matches(greetMethod, MemberServiceImpl.class)).isTrue();
  }

  @Test
  void patternMatchStar2() {
    final var patternMatchExpression = "execution(* *nal*(..))";
    pointcut.setExpression(patternMatchExpression);

    assertThat(pointcut.matches(greetMethod, MemberServiceImpl.class)).isTrue();
  }


}
