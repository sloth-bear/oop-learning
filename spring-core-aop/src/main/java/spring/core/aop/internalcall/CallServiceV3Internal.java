package spring.core.aop.internalcall;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CallServiceV3Internal {

  public void internal() {
    log.info("call internal");
  }
}
