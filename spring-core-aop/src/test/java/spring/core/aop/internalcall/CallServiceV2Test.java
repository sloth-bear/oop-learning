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
class CallServiceV2Test {

  @Autowired
  CallServiceV2Lazy callServiceV2;

  @Test
  @DisplayName("target claÎss - 자기 메서드 호출 대신 자기를 주입 받고, 생성자 주입을 사용하기 위해 bean 지연 조회")
  void callServiceAopTest() {
    callServiceV2.external();
  }

}