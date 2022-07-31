package spring.core.aop.goods;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import spring.core.aop.goods.aop.RetryAspect;
import spring.core.aop.goods.aop.TraceAspect;

@Slf4j
@SpringBootTest
//@Import(TraceAspect.class)
@Import({TraceAspect.class, RetryAspect.class})
public class GoodsAopTest {

  @Autowired
  GoodsService goodsService;

  @Test
  void test() {
    for (int i = 0; i < 5; i++) {
      log.info("client request i={}", i);
      goodsService.create("data" + i);
    }
  }
}
