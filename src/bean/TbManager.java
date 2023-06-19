package bean;


public class TbManager {

  private String id;
  private String manager;
  private String pwd;

  public TbManager() {
  }

  public TbManager(String id, String manager, String pwd) {
    this.id = id;
    this.manager = manager;
    this.pwd = pwd;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }


  public String getManager() {
    return manager;
  }

  public void setManager(String manager) {
    this.manager = manager;
  }


  public String getPwd() {
    return pwd;
  }

  public void setPwd(String pwd) {
    this.pwd = pwd;
  }

}
