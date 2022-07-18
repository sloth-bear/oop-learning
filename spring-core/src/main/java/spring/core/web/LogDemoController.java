package spring.core.web;

import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.core.common.MyLogger;

@RestController
@RequestMapping("/log-demo")
@RequiredArgsConstructor
public class LogDemoController {

  private final LogDemoService logDemoService;
  private final MyLogger logger;

  @GetMapping
  public String logDemo(final HttpServletRequest request) {
    logger.setRequestURL(request.getRequestURL().toString());

    System.out.println("my logger: " + logger.getClass());

    logger.log("controller test");
    logDemoService.logic("testId");
    return "OK";
  }
}
