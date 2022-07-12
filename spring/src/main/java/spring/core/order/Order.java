package spring.core.order;

public class Order {

  private Long orderId;
  private String goodsName;
  private int goodsPrice;
  private int discountAmount;

  public Order(final Long orderId, final String goodsName, final int goodsPrice,
      final int discountAmount) {
    this.orderId = orderId;
    this.goodsName = goodsName;
    this.goodsPrice = goodsPrice;
    this.discountAmount = discountAmount;
  }

  public int calculatePrice() {
    return goodsPrice - discountAmount;
  }

  public Long getOrderId() {
    return orderId;
  }

  public void setOrderId(final Long orderId) {
    this.orderId = orderId;
  }

  public String getGoodsName() {
    return goodsName;
  }

  public void setGoodsName(final String goodsName) {
    this.goodsName = goodsName;
  }

  public int getGoodsPrice() {
    return goodsPrice;
  }

  public void setGoodsPrice(final int goodsPrice) {
    this.goodsPrice = goodsPrice;
  }

  public int getDiscountAmount() {
    return discountAmount;
  }

  public void setDiscountAmount(final int discountAmount) {
    this.discountAmount = discountAmount;
  }

  @Override
  public String toString() {
    return "Order{" +
        "orderId=" + orderId +
        ", goodsName='" + goodsName + '\'' +
        ", goodsPrice=" + goodsPrice +
        ", discountPrice=" + discountAmount +
        '}';
  }
}
