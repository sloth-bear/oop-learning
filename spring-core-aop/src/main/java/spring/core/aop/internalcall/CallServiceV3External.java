package spring.core.aop.internalcall;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CallServiceV3External {

  private final CallServiceV3Internal internalService;

  public void external() {
    log.info("call external");
    internalService.internal();
  }

}
