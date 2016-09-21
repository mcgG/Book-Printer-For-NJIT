package bean;

/**
 * Created by zhaokaihao on 12/3/15.
 */
public class BookBean {

  private int id;
  private String UCID;
  private String bookName;
  private String major;
  private String courseLevel;
  private String professor;
  private String addDate;
  private int sharedTimes;
  private int privateOrShare;
  private String filePath;
  private String phyPath;

  public String getPhyPath() {
    return phyPath;
  }

  public void setPhyPath(String phyPath) {
    this.phyPath = phyPath;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getCourseLevel() {
    return courseLevel;
  }
  public void setCourseLevel(String courseLevel) {
    this.courseLevel = courseLevel;
  }
  public String getFilePath() {
    return filePath;
  }

  public void setFilePath(String filePath) {
    this.filePath = filePath;
  }

  public String getUCID() {
    return UCID;
  }

  public void setUCID(String UCID) {
    this.UCID = UCID;
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

  public int getPrivateOrShare() {
    return privateOrShare;
  }

  public void setPrivateOrShare(int privateOrShare) {
    this.privateOrShare = privateOrShare;
  }

}
