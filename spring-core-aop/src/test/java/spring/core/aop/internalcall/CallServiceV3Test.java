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
class CallServiceV3Test {

  @Autowired
  CallServiceV3External callServiceV3;

  @Test
  @DisplayName("target class - 자기 메서드 호출 대신 구조 분리")
  void callServiceAopTest() {
    callServiceV3.external();
  }

}