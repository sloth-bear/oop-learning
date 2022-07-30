package spring.core.aop.pointcut;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import spring.core.aop.member.MemberService;

/**
 * application.properties spring.aop.proxy-target-class=true CGLIB (default)
 * spring.aop.proxy-target-class=false JDK Dynamic proxy
 */
@Slf4j
@SpringBootTest(properties = "spring.aop.proxy-target-class=true")
//@SpringBootTest(properties = "spring.aop.proxy-target-class=false")
@Import(ThisAndTargetTest.ThisAndTargetAspect.class)
public class ThisAndTargetTest {

  @Autowired
  MemberService memberService;

  @Test
  @DisplayName("JDK dynamic proxy / CGLIB - this expression에 대해 JDK proxy는 interface 기반이라 자식 타입 기반으로 매칭될 수 없다.")
  void success() {
    log.info("memberService Proxy={}", memberService.getClass());
    memberService.externalGreet("word!");
  }

  @Slf4j
  @Aspect
  static class ThisAndTargetAspect {

    @Around("this(spring.core.aop.member.MemberService)")
    public Object doThisInterface(final ProceedingJoinPoint joinPoint) throws Throwable {
      log.info("[this-interface] {}", joinPoint.getSignature());
      return joinPoint.proceed();
    }

    @Around("target(spring.core.aop.member.MemberService)")
    public Object doTargetInterface(final ProceedingJoinPoint joinPoint) throws Throwable {
      log.info("[target-interface] {}", joinPoint.getSignature());
      return joinPoint.proceed();
    }

    @Around("this(spring.core.aop.member.MemberServiceImpl)")
    public Object doThisConcrete(final ProceedingJoinPoint joinPoint) throws Throwable {
      log.info("[this-concrete] {}", joinPoint.getSignature());
      return joinPoint.proceed();
    }

    @Around("target(spring.core.aop.member.MemberServiceImpl)")
    public Object doTargetConcrete(final ProceedingJoinPoint joinPoint) throws Throwable {
      log.info("[target-concrete] {}", joinPoint.getSignature());
      return joinPoint.proceed();
    }
  }
}
