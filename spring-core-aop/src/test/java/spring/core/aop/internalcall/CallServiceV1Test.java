package spring.core.aop.internalcall;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import spring.core.aop.internalcall.aop.CallLogAspect;

@Slf4j
@SpringBootTest
@Import(CallLogAspect.class)
class CallServiceV1Test {

  @Autowired
  CallServiceV1Target callServiceV1ITarget;

  @Test
  @DisplayName("proxy target class 내에서 자기 메서드를 호출하지 않는 대신 스스로를 주입 받아 (proxy) 해당 객체의 메서드를 사용한다.")
  void callServiceAopTest() {
    callServiceV1ITarget.external();
  }

}