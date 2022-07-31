package spring.core.aop.goods;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.core.aop.goods.annotation.Trace;

@Service
@RequiredArgsConstructor
public class GoodsService {

  private final GoodsRepository goodsRepository;

  @Trace
  public void create(final String itemId) {
    goodsRepository.save(itemId);
  }
}
