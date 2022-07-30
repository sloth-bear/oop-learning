package spring.core.aop.pointcut.execution;

import static org.assertj.core.api.Assertions.assertThat;

import java.lang.reflect.Method;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import spring.core.aop.member.MemberServiceImpl;

public class PackageExecutionTest {


  AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
  Method greetMethod;

  @BeforeEach
  public void init() throws NoSuchMethodException {
    greetMethod = MemberServiceImpl.class.getMethod("externalGreet", String.class);
  }

  @Test
  void packageExactMatch() {
    final var packageExactMatchExpression = "execution(*  spring.core.aop.member.MemberServiceImpl.externalGreet(..))";
    pointcut.setExpression(packageExactMatchExpression);

    assertThat(pointcut.matches(greetMethod, MemberServiceImpl.class)).isTrue();
  }

  @Test
  void packageExactMatch2() {
    final var packageExactMatchExpression = "execution(*  spring.core.aop.member.*.*(..))";
    pointcut.setExpression(packageExactMatchExpression);

    assertThat(pointcut.matches(greetMethod, MemberServiceImpl.class)).isTrue();
  }

  @Test
  void packageNoMatch() {
    final var packageExactMatchExpression = "execution(*  spring.core.member.*.*(..))";
    pointcut.setExpression(packageExactMatchExpression);

    assertThat(pointcut.matches(greetMethod, MemberServiceImpl.class)).isFalse();
  }

  @Test
  void allPackageMatch() {
    final var allWithSubPackage = "execution(*  spring.core..*.*(..))";
    pointcut.setExpression(allWithSubPackage);

    assertThat(pointcut.matches(greetMethod, MemberServiceImpl.class)).isTrue();
  }

}
