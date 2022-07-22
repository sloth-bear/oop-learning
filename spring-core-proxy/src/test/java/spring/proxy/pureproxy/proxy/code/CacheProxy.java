package spring.proxy.pureproxy.proxy.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CacheProxy implements Subject {

  private final Subject target;
  private String cacheValue;

  public CacheProxy(final Subject target) {
    this.target = target;
  }

  @Override
  public String operate() {
    log.info("CacheProxy 호출");

    if (cacheValue == null) {
      cacheValue = target.operate();
    }
    
    return cacheValue;
  }
}
