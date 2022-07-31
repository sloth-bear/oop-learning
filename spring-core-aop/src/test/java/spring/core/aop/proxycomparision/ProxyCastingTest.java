package spring.core.aop.proxycomparision;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;
import spring.core.aop.member.MemberService;
import spring.core.aop.member.MemberServiceImpl;

public class ProxyCastingTest {

  @Test
  void jdkProxyCasting() {
    final MemberServiceImpl target = new MemberServiceImpl();
    final ProxyFactory proxyFactory = new ProxyFactory(target);
    proxyFactory.setProxyTargetClass(false);

    final Object jdkProxy = proxyFactory.getProxy();

    assertThat((MemberService) jdkProxy).isInstanceOf(MemberService.class);
    assertThatThrownBy(() -> {
      final MemberServiceImpl castedImpl = (MemberServiceImpl) jdkProxy;
      castedImpl.externalGreet("world");
    }).isInstanceOf(ClassCastException.class);
  }

  @Test
  void cglibProxyCasting() {
    final MemberServiceImpl target = new MemberServiceImpl();
    final ProxyFactory proxyFactory = new ProxyFactory(target);
    proxyFactory.setProxyTargetClass(true);

    final Object cglibProxy = proxyFactory.getProxy();

    assertThat((MemberService) cglibProxy).isInstanceOf(MemberService.class);
    assertThat((MemberServiceImpl) cglibProxy).isInstanceOf(MemberServiceImpl.class);
  }
}
