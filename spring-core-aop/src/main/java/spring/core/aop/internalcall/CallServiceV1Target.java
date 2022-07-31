package spring.core.aop.internalcall;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CallServiceV1Target {

  private CallServiceV1Target callServiceV1Target;

  /**
   * 생성자 주입이 불가능한 이유는 아직 초기화되지 않은 상태에서 자기를 참조해서 순환참조 문제가 발생하기 때문
   */
  @Autowired
  public void setCallServiceV1ITarget(CallServiceV1Target callServiceV1Target) {
    log.info("CallServiceV1Target setter - inject proxy={}", callServiceV1Target.getClass());
    this.callServiceV1Target = callServiceV1Target;
  }

  public void external() {
    log.info("call external");
    callServiceV1Target.internal();
  }

  public void internal() {
    log.info("call internal");
  }
}
