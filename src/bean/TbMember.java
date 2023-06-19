package bean;


public class TbMember {

  private String id;
  private String username;
  private String truename;
  private String password;
  private String city;
  private String address;
  private String postcode;
  private String cardno;
  private String cardtype;
  private String tel;
  private String email;
  private String freeze;
  private String grade;

  public TbMember() {
  }

  public TbMember(String id, String username, String truename, String password, String city, String address, String postcode, String cardno, String cardtype, String tel, String email, String freeze, String grade) {
    this.id = id;
    this.username = username;
    this.truename = truename;
    this.password = password;
    this.city = city;
    this.address = address;
    this.postcode = postcode;
    this.cardno = cardno;
    this.cardtype = cardtype;
    this.tel = tel;
    this.email = email;
    this.freeze = freeze;
    this.grade = grade;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }


  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }


  public String getTruename() {
    return truename;
  }

  public void setTruename(String truename) {
    this.truename = truename;
  }


  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }


  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
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


  public String getCardno() {
    return cardno;
  }

  public void setCardno(String cardno) {
    this.cardno = cardno;
  }


  public String getCardtype() {
    return cardtype;
  }

  public void setCardtype(String cardtype) {
    this.cardtype = cardtype;
  }


  public String getTel() {
    return tel;
  }

  public void setTel(String tel) {
    this.tel = tel;
  }


  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }


  public String getFreeze() {
    return freeze;
  }

  public void setFreeze(String freeze) {
    this.freeze = freeze;
  }


  public String getGrade() {
    return grade;
  }

  public void setGrade(String grade) {
    this.grade = grade;
  }

}
