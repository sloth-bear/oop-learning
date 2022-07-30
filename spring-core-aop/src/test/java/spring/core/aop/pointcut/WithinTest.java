package spring.core.aop.pointcut;

import static org.assertj.core.api.Assertions.assertThat;

import java.lang.reflect.Method;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import spring.core.aop.member.MemberServiceImpl;

@Slf4j
public class WithinTest {

  AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
  Method greetMethod;

  @BeforeEach
  public void init() throws NoSuchMethodException {
    greetMethod = MemberServiceImpl.class.getMethod("externalGreet", String.class);
  }

  @Test
  @DisplayName("within 지시자로 타입 매칭")
  void withinExact() {
    pointcut.setExpression("within(spring.core.aop.member.MemberServiceImpl)");
    assertThat(pointcut.matches(greetMethod, MemberServiceImpl.class)).isTrue();
  }

  @Test
  void withinExactStar() {
    pointcut.setExpression("within(spring.core.aop.member.*Service*)");
    assertThat(pointcut.matches(greetMethod, MemberServiceImpl.class)).isTrue();
  }

  @Test
  void withinExactSubPackage() {
    pointcut.setExpression("within(spring.core.aop..*)");
    assertThat(pointcut.matches(greetMethod, MemberServiceImpl.class)).isTrue();
  }

  @Test
  @DisplayName("타겟의 타입만 적용, super type 사 불가")
  void withinSuperTypeFalse() {
    pointcut.setExpression("within(spring.core.aop.member.MemberService)");
    assertThat(pointcut.matches(greetMethod, MemberServiceImpl.class)).isFalse();
  }

}
