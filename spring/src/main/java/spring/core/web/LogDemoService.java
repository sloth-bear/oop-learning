package spring.core.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.core.common.MyLogger;

@Service
@RequiredArgsConstructor
public class LogDemoService {

  private final MyLogger logger;

  public void logic(final String id) {
    logger.log("service id: " + id);
  }
}
