package spring.proxy.app.v3;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v3/orders")
public class OrderControllerV3 {

  private final OrderServiceV3 orderServiceV3;

  @GetMapping
  public String orders(final String itemId) {
    orderServiceV3.saveItem(itemId);
    return "OK";
  }

  @GetMapping("/no-logs")
  public String noLogs() {
    return "OK";
  }
}
