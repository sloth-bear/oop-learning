package spring.core.aop.internalcall;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CallServiceV2Lazy {

  private final ObjectProvider<CallServiceV2Lazy> callServiceV2Provider;

  public CallServiceV2Lazy(final ObjectProvider<CallServiceV2Lazy> callServiceV2Provider) {
    this.callServiceV2Provider = callServiceV2Provider;
  }

  public void external() {
    log.info("call external");
    final var serviceV2 = callServiceV2Provider.getObject();
    serviceV2.internal();
  }

  public void internal() {
    log.info("call internal");
  }
}
