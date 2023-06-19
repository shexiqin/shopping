package bean;


public class TbArealink {

  private String id;
  private String name;
  private String pid;

  public TbArealink() {
  }

  public TbArealink(String id, String name, String pid) {
    this.id = id;
    this.name = name;
    this.pid = pid;
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


  public String getPid() {
    return pid;
  }

  public void setPid(String pid) {
    this.pid = pid;
  }

}
