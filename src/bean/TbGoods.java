package bean;


public class TbGoods {

  private String id;
  private String goodsname;
  private String price;
  private String picture;
  private String credate;
  private String introduce;
  private String classname;
  private String unLine;

  public TbGoods() {
  }

  public TbGoods(String id, String goodsname, String price, String picture, String credate, String introduce, String classname, String unLine) {
    this.id = id;
    this.goodsname = goodsname;
    this.price = price;
    this.picture = picture;
    this.credate = credate;
    this.introduce = introduce;
    this.classname = classname;
    this.unLine = unLine;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }


  public String getGoodsname() {
    return goodsname;
  }

  public void setGoodsname(String goodsname) {
    this.goodsname = goodsname;
  }


  public String getPrice() {
    return price;
  }

  public void setPrice(String price) {
    this.price = price;
  }


  public String getPicture() {
    return picture;
  }

  public void setPicture(String picture) {
    this.picture = picture;
  }


  public String getCredate() {
    return credate;
  }

  public void setCredate(String credate) {
    this.credate = credate;
  }


  public String getIntroduce() {
    return introduce;
  }

  public void setIntroduce(String introduce) {
    this.introduce = introduce;
  }


  public String getClassname() {
    return classname;
  }

  public void setClassname(String classname) {
    this.classname = classname;
  }


  public String getUnLine() {
    return unLine;
  }

  public void setUnLine(String unLine) {
    this.unLine = unLine;
  }

}
