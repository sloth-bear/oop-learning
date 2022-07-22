package spring.proxy.app.v1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
@RequestMapping
public interface OrderControllerV1 {

  @GetMapping("/v1/orders")
  String orders(@RequestParam("itemId") String itemId);

  @GetMapping("/v1/orders/no-logs")
  String noLogs();
}
