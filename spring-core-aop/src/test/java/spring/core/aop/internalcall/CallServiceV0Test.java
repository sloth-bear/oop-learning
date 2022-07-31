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
class CallServiceV0Test {

  @Autowired
  CallServiceV0 callServiceV0;

  @Test
  @DisplayName("proxy target class 내에서 자기 메서드를 호출할 경우 aop 적용이 안 된다.")
  void callServiceAopTest() {
//    log.info("class: {}", callServiceV0.getClass());
    callServiceV0.external();
    callServiceV0.internal();
  }

}