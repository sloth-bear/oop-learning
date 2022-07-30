package spring.core.aop.pointcut;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import spring.core.aop.member.MemberService;
import spring.core.aop.member.MemberServiceImpl;
import spring.core.aop.member.annotation.ClassAop;
import spring.core.aop.member.annotation.MethodAop;

@Slf4j
@SpringBootTest
@Import(ParameterTest.ParameterAspect.class)
public class ParameterTest {

  @Autowired
  MemberService memberService;

  @Test
  void success() {
    log.info("memberService Proxy={}", memberService.getClass());
    memberService.externalGreet("word");
  }

  @Slf4j
  @Aspect
  static class ParameterAspect {

    @Pointcut("execution(* spring.core.aop.member..*.*(..))")
    private void allMember() {
    }

    @Around("allMember()")
    public Object doLogArgFromJoinPoint(final ProceedingJoinPoint joinPoint) throws Throwable {
      final var arg1 = joinPoint.getArgs()[0];
      log.info("[doLogArgFromJoinPoint] {}, arg={}", joinPoint.getSignature(), arg1);
      return joinPoint.proceed();
    }

    @Around("allMember() && args(arg, ..)")
    public Object doLogArgByExpression(final ProceedingJoinPoint joinPoint, final Object arg)
        throws Throwable {
      log.info("[doLogArgByExpression] {}, arg={}", joinPoint.getSignature(), arg);
      return joinPoint.proceed();
    }

    @Before("allMember() && args(arg, ..)")
    public void dogLogArgsBefore(final String arg) {
      log.info("[dogLogArgsBefore] arg={}", arg);
    }

    @Before(value = "allMember() && this(obj)", argNames = "joinPoint,obj")
    public void thisArgs(final JoinPoint joinPoint, final MemberServiceImpl obj) {
      log.info("[thisArgs] {} obj={}", joinPoint.getSignature(), obj.getClass());
    }

    @Before("allMember() && target(obj)")
    public void targetArgs(final JoinPoint joinPoint, final MemberServiceImpl obj) {
      log.info("[targetArgs] {} obj={}", joinPoint.getSignature(), obj.getClass());
    }

    @Before("allMember() && @target(annotation)")
    public void atTargetArgs(final JoinPoint joinPoint, final ClassAop annotation) {
      log.info("[@targetArgs] {} annotation={}", joinPoint.getSignature(), annotation);
    }

    @Before("allMember() && @within(annotation)")
    public void atWithinArgs(final JoinPoint joinPoint, final ClassAop annotation) {
      log.info("[@withinArgs] {} annotation={}", joinPoint.getSignature(), annotation);
    }

    @Before("allMember() && @annotation(annotation)")
    public void atAnnotationArgs(final JoinPoint joinPoint, final MethodAop annotation) {
      log.info("[@annotationArgs] {} annotationValue={}", joinPoint.getSignature(),
          annotation.value());
    }
  }
}
