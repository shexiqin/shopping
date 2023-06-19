package bean;


public class TbUsercar {

  private String id;
  private String goodsid;
  private String memberid;
  private String count;
  private String goodsName;
  private String price;

  public TbUsercar() {
  }

  public TbUsercar(String id, String goodsid, String memberid, String count) {
    this.id = id;
    this.goodsid = goodsid;
    this.memberid = memberid;
    this.count = count;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }


  public String getGoodsid() {
    return goodsid;
  }

  public void setGoodsid(String goodsid) {
    this.goodsid = goodsid;
  }


  public String getMemberid() {
    return memberid;
  }

  public void setMemberid(String memberid) {
    this.memberid = memberid;
  }


  public String getCount() {
    return count;
  }

  public void setCount(String count) {
    this.count = count;
  }

  public String getGoodsName() {
    return goodsName;
  }

  public void setGoodsName(String goodsName) {
    this.goodsName = goodsName;
  }

  public String getPrice() {
    return price;
  }

  public void setPrice(String price) {
    this.price = price;
  }
}
