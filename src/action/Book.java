package action;

import bean.BookBean;
import bean.PrintBookBean;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaokaihao on 12/3/15.
 */
public class Book {

  public static boolean setPrintedByName(String bookName) {
    Connection conn = DBConnection.getConnection();
    String sql = "update PRINTBOOK set IsPrinted=1 where BookName=?";
    PreparedStatement pstmt;
    int i = 0;
    try {
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, bookName);
      i = pstmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    if(i != 0)
      return true;
    return false;
  }

  public static boolean addBook(BookBean bean) {
    if(bean != null) {
      if (Check.checkBookAvailable(bean.getBookName(), bean.getUCID())) {
        Connection conn = DBConnection.getConnection();
        String sql = "insert into BOOKS (BookName, UCID, Major,CourseLevel, Professor" +
            ",AddDate, SharedTimes, PrivateOrShare, FilePath, PhyPath) " +
            "values (?,?,?,?,?,?,?,?,?,?)";
        String major = GetInfo.getMajorByCode(bean.getMajor()).getMajorName();
        PreparedStatement pstmt = null;
        int i = 0;
        try {
          pstmt = conn.prepareStatement(sql);
          pstmt.setString(1, bean.getBookName());
          pstmt.setString(2, bean.getUCID());
          pstmt.setString(3, bean.getMajor());
          pstmt.setString(4, bean.getCourseLevel());
          pstmt.setString(5, bean.getProfessor());
          pstmt.setString(6, bean.getAddDate());
          pstmt.setInt(7, bean.getSharedTimes());
          pstmt.setInt(8, bean.getPrivateOrShare());
          pstmt.setString(9, bean.getFilePath());
          pstmt.setString(10, bean.getPhyPath());
          i = pstmt.executeUpdate();
        } catch (SQLException e) {
          e.printStackTrace();
        }
        if (i != 0) {
          boolean flag = Book.addPrintBook(bean.getBookName(), bean.getUCID());
          if(flag)
            return true;
        }
      }
    }
    return false;
  }

  public static boolean deleteBook(String bookName, String UCID) {
    if(!bookName.equals("") && !UCID.equals("")) {
      Connection conn = DBConnection.getConnection();
      String sql = "delete from BOOKS where BookName=? and UCID=?";
      PreparedStatement pstmt = null;
      int i = 0;
      try {
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, bookName);
        pstmt.setString(2, UCID);
        i = pstmt.executeUpdate();
      } catch (SQLException e) {
        e.printStackTrace();
      }
      if(i != 0) {
        deleteShare(bookName, UCID);
        return true;
      }
    }
    return false;
  }

  public static void deleteShare(String bookName, String UCID) {
    if(!bookName.equals("") && !UCID.equals("")) {
      Connection conn = DBConnection.getConnection();
      String sql = "delete from SHAREBOOKINFO where BookName=? and UCID=?";
      PreparedStatement pstmt = null;
      int i=0;
      try {
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, bookName);
        pstmt.setString(2, UCID);
        i = pstmt.executeUpdate();
        System.out.println(i);
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  public static boolean shareBook(String bookName, String UCID) {
    System.out.println(bookName + UCID);
    if(!bookName.equals("") && !UCID.equals("")) {
      Connection conn = DBConnection.getConnection();
      String sql1 = "insert into SHAREBOOKINFO (BookName, UCID) values (?,?)";
      String sql2 = "update BOOKS set PrivateOrShare=1 where BookName=? and UCID=?";
      PreparedStatement pstmt;
      PreparedStatement pstmt2;
      int i1 = 0; int i2 = 0;
      try {
        pstmt = conn.prepareStatement(sql1);
        pstmt.setString(1, bookName);
        pstmt.setString(2, UCID);
        i1 = pstmt.executeUpdate();

        pstmt2 = conn.prepareStatement(sql2);
        pstmt2.setString(1, bookName);
        pstmt2.setString(2, UCID);
        i2 = pstmt2.executeUpdate();
      } catch (SQLException e) {
        e.printStackTrace();
      }
      if(i1 != 0 && i2 != 0) {
        addSharedTimes(bookName, UCID);
        return true;
      }
    }
      return false;
  }

  public static void addSharedTimes(String bookName, String UCID) {
    if(!bookName.equals("") && !UCID.equals("")) {
      Connection conn = DBConnection.getConnection();
      String sql = "update BOOKS set SharedTimes=SharedTimes+1 where BookName=? and UCID=?";
      PreparedStatement pstmt = null;
      try {
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, bookName);
        pstmt.setString(2, UCID);
        pstmt.executeUpdate();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  public static boolean addPrintBook(String bookName, String UCID) {
    BookBean bean = GetInfo.getBookByNameAndUCID(bookName, UCID);
    List<BookBean> list;
    if(bean != null) {
      list = PDFFile.splitFile(bean);
      System.out.println(bean.getFilePath());
      Connection conn = DBConnection.getConnection();
      int flag = 0;
      for(int i=0; i<list.size(); i++) {
        String sql = "insert into PRINTBOOK (UCID, BookName, FilePath, PhyPath, SubFileName)" +
            "values (?,?,?,?,?)";
        PreparedStatement pstmt;
        flag = 0;
        try {
          pstmt = conn.prepareStatement(sql);
          pstmt.setString(1, UCID);
          pstmt.setString(2, bookName);
          pstmt.setString(3, list.get(i).getPhyPath());
          pstmt.setString(4, list.get(i).getFilePath());
          pstmt.setString(5, PDFFile.getFileName(list.get(i).getPhyPath()));
          flag = pstmt.executeUpdate();
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }
      if(flag != 0) {

        return true;
      }
    }
    return false;
  }

  public static boolean setPrinted(String bookName, String UCID) {
      Connection conn = DBConnection.getConnection();
      int flag = 0;
        String sql = "update PRINTBOOK set IsPrinted = 1 where SubFileName = ? and UCID = ?";
        PreparedStatement pstmt;
        flag = 0;
        try {
          pstmt = conn.prepareStatement(sql);
          pstmt.setString(1, bookName);
          pstmt.setString(2, UCID);
          flag = pstmt.executeUpdate();
        } catch (SQLException e) {
          e.printStackTrace();
        }
      if(flag != 0) {
        return true;
      }
    return false;
  }





  public static void main(String args[]) {
    BookBean bean = new BookBean();
    bean.setBookName("numpy-userguide.pdf");
    bean.setUCID("kz82");
    bean.setMajor("CS");
    bean.setProfessor("GB");
    bean.setAddDate("12/3/2015");
    bean.setFilePath("/Users/zhaokaihao/Documents/workspace/BookPrinter/test/numpy-userguide.pdf");
    //System.out.println(addBook(bean));
    //System.out.println(deleteBook("Java", "kz82"));
    //System.out.println(shareBook("numpy-userguide.pdf", "kz82"));
    //System.out.println(addPrintBook("numpy-userguide.pdf", "kz82"));
  }
}
