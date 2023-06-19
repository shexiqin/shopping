package bean;


public class TbOrder {

  private String id;
  private String memberid;
  private String ordercode;
  private String bnumber;
  private String username;
  private String address;
  private String postcode;
  private String tel;
  private String pay;
  private String carry;
  private String orderdate;
  private String enforce;
  private String bz;
  private String name;

  public TbOrder() {
  }

  public TbOrder(String id, String memberid, String ordercode, String bnumber, String username, String address, String postcode, String tel, String pay, String carry, String orderdate, String enforce, String bz) {
    this.id = id;
    this.memberid = memberid;
    this.ordercode = ordercode;
    this.bnumber = bnumber;
    this.username = username;
    this.address = address;
    this.postcode = postcode;
    this.tel = tel;
    this.pay = pay;
    this.carry = carry;
    this.orderdate = orderdate;
    this.enforce = enforce;
    this.bz = bz;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }


  public String getMemberid() {
    return memberid;
  }

  public void setMemberid(String menberid) {
    this.memberid = menberid;
  }


  public String getOrdercode() {
    return ordercode;
  }

  public void setOrdercode(String ordercode) {
    this.ordercode = ordercode;
  }


  public String getBnumber() {
    return bnumber;
  }

  public void setBnumber(String bnumber) {
    this.bnumber = bnumber;
  }


  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }


  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }


  public String getPostcode() {
    return postcode;
  }

  public void setPostcode(String postcode) {
    this.postcode = postcode;
  }


  public String getTel() {
    return tel;
  }

  public void setTel(String tel) {
    this.tel = tel;
  }


  public String getPay() {
    return pay;
  }

  public void setPay(String pay) {
    this.pay = pay;
  }


  public String getCarry() {
    return carry;
  }

  public void setCarry(String carry) {
    this.carry = carry;
  }


  public String getOrderdate() {
    return orderdate;
  }

  public void setOrderdate(String orderdate) {
    this.orderdate = orderdate;
  }


  public String getEnforce() {
    return enforce;
  }

  public void setEnforce(String enforce) {
    this.enforce = enforce;
  }


  public String getBz() {
    return bz;
  }

  public void setBz(String bz) {
    this.bz = bz;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
