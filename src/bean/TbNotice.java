package bean;


public class TbNotice {

  private String id;
  private String title;
  private String content;
  private String creatdate;
  private String enddate;
  private String addfile;

  public TbNotice() {
  }

  public TbNotice(String id, String title, String content, String creatdate, String enddate, String addfile) {
    this.id = id;
    this.title = title;
    this.content = content;
    this.creatdate = creatdate;
    this.enddate = enddate;
    this.addfile = addfile;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }


  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }


  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }


  public String getCreatdate() {
    return creatdate;
  }

  public void setCreatdate(String creatdate) {
    this.creatdate = creatdate;
  }


  public String getEnddate() {
    return enddate;
  }

  public void setEnddate(String enddate) {
    this.enddate = enddate;
  }


  public String getAddfile() {
    return addfile;
  }

  public void setAddfile(String addfile) {
    this.addfile = addfile;
  }

}
