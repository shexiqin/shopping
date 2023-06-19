package bean;


public class TbType {

  private String id;
  private String name;
  private String flag;

  public TbType() {
  }

  public TbType(String id, String name, String flag) {
    this.id = id;
    this.name = name;
    this.flag = flag;
  }


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getFlag() {
    return flag;
  }

  public void setFlag(String flag) {
    this.flag = flag;
  }

}
