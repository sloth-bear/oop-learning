package spring.core.aop.order.aop;

import org.aspectj.lang.annotation.Pointcut;

public class PointCut {

  @Pointcut("execution(* spring.core.aop.order..*(..))")
  public void allOrder() {
  }

  @Pointcut("execution(* *..*Service.*(..))")
  public void allService() {
  }

  @Pointcut("allOrder() && allService()")
  public void allOrderAndService() {
  }

}
