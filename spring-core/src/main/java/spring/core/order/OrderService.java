package spring.core.order;

public interface OrderService {

  Order create(Long memberId, String goodsName, int goodsPrice);
}
