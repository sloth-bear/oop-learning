package spring.core.aop.goods;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import spring.core.aop.goods.annotation.Retry;
import spring.core.aop.goods.annotation.Trace;

@Slf4j
@Repository
public class GoodsRepository {

  private static int seq = 0;

  /**
   * 5번 중 1번은 실패하도록 의도적으로 만들어졌다.
   */
  @Trace
  @Retry(4)
  @SuppressWarnings("UnusedReturnValue")
  public String save(final String itemId) {
    seq++;
    log.info("[GoodsRepository.save(), count: {}", seq);

    if (seq % 5 == 0) {
      throw new IllegalStateException("5회 요청 시마다 실패하도록 예외처리");
    }
    return "ok";
  }
}
