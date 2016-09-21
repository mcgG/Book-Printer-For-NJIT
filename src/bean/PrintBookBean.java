package bean;

/**
 * Created by zhaokaihao on 12/6/15.
 */
public class PrintBookBean {
  private String UCID;
  private String bookName;
  private String filePath;
  private String PhyPath;

  public String getPhyPath() {
    return PhyPath;
  }

  public void setPhyPath(String phyPath) {
    PhyPath = phyPath;
  }

  private int isPrinted;

  public String getBookName() {
    return bookName;
  }

  public void setBookName(String bookName) {
    this.bookName = bookName;
  }

  public String getFilePath() {
    return filePath;
  }

  public void setFilePath(String filePath) {
    this.filePath = filePath;
  }

  public int getIsPrinted() {
    return isPrinted;
  }

  public void setIsPrinted(int isPrinted) {
    this.isPrinted = isPrinted;
  }

  public String getUCID() {

    return UCID;
  }

  public void setUCID(String UCID) {
    this.UCID = UCID;
  }
}
