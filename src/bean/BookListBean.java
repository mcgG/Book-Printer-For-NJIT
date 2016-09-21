package bean;

/**
 * Created by zhaokaihao on 12/7/15.
 */
public class BookListBean {
  private int id;
  private String bookName;
  private String major;
  private String professor;
  private String courseLevel;
  private String UCID;
  private String addDate;
  private int sharedTimes;
  private String htmlButton;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getBookName() {
    return bookName;
  }

  public void setBookName(String bookName) {
    this.bookName = bookName;
  }

  public String getMajor() {
    return major;
  }

  public void setMajor(String major) {
    this.major = major;
  }

  public String getProfessor() {
    return professor;
  }

  public void setProfessor(String professor) {
    this.professor = professor;
  }

  public String getCourseLevel() {
    return courseLevel;
  }

  public void setCourseLevel(String courseLevel) {
    this.courseLevel = courseLevel;
  }

  public String getUCID() {
    return UCID;
  }

  public void setUCID(String UCID) {
    this.UCID = UCID;
  }

  public String getAddDate() {
    return addDate;
  }

  public void setAddDate(String addDate) {
    this.addDate = addDate;
  }

  public int getSharedTimes() {
    return sharedTimes;
  }

  public void setSharedTimes(int sharedTimes) {
    this.sharedTimes = sharedTimes;
  }

  public String getHtmlButton() {
    return htmlButton;
  }

  public void setHtmlButton() {
    this.htmlButton = "<button id='"+id+"' type='button' class='save-btn'>Save</button>";
  }
}
