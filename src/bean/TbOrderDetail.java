package bean;


public class TbOrderDetail {

  private String id;
  private String orderid;
  private String goodsid;
  private String goodsName;
  private String price;
  private String numbers;

  public TbOrderDetail(String id, String orderid, String goodsid, String price, String numbers) {
    this.id = id;
    this.orderid = orderid;
    this.goodsid = goodsid;
    this.price = price;
    this.numbers = numbers;
  }

  public TbOrderDetail() {

  }

  public String getGoodsName() {
    return goodsName;
  }

  public void setGoodsName(String goodsName) {
    this.goodsName = goodsName;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }


  public String getOrderid() {
    return orderid;
  }

  public void setOrderid(String orderid) {
    this.orderid = orderid;
  }


  public String getGoodsid() {
    return goodsid;
  }

  public void setGoodsid(String goodsid) {
    this.goodsid = goodsid;
  }


  public String getPrice() {
    return price;
  }

  public void setPrice(String price) {
    this.price = price;
  }


  public String getNumbers() {
    return numbers;
  }

  public void setNumbers(String numbers) {
    this.numbers = numbers;
  }

}
