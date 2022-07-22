package spring.proxy.app.v2;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@ResponseBody
@RequestMapping
@RequiredArgsConstructor
@SuppressWarnings("SpringMVCViewInspection")
public class OrderControllerV2 {

  private final OrderServiceV2 orderServiceV2;

  @GetMapping("/v2/orders")
  public String orders(final String itemId) {
    orderServiceV2.saveItem(itemId);
    return "OK";
  }

  @GetMapping("/v2/orders/no-logs")
  public String noLogs() {
    return "OK";
  }
}
