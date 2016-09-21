package action;

import bean.*;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaokaihao on 12/5/15.
 */
public class GetInfo {

  public static UserInfoBean getUserInfo(String UCID) {
    UserInfoBean bean = new UserInfoBean();
    Connection conn = DBConnection.getConnection();
    String sql = "select * from USERS where UCID=?";
    PreparedStatement pstmt;
    ResultSet rs;
    try {
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, UCID);
      rs = pstmt.executeQuery();
      while(rs.next()) {
        bean.setUCID(rs.getString("UCID"));
        bean.setUserName(rs.getString("UserName"));
        bean.setMajor(rs.getString("Major"));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return bean;
  }

  public static String getProfessorByID(int pID) {
    //ProfessorBean bean = new ProfessorBean();
    String profName=null;
    Connection conn = DBConnection.getConnection();
    String sql = "select PID, PName from PROFESSORS where PID = ?";
    PreparedStatement pstmt;
    ResultSet rs;
    try {
      pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1, pID);
      rs = pstmt.executeQuery();
      while(rs.next()) {
        //bean.setpID(rs.getInt("PID"));
        //bean.setpName(rs.getString("PName"));
        profName = rs.getString("PName");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    System.out.println("professor: "+ profName);
    return profName;
  }

  public static List<ProfessorBean> getAllProfessor() {
    List<ProfessorBean> list = new ArrayList<ProfessorBean>();
    Connection conn = DBConnection.getConnection();
    String sql = "select * from PROFESSORS";
    PreparedStatement pstmt;
    ResultSet rs;
    try {
      pstmt = conn.prepareStatement(sql);
      rs = pstmt.executeQuery();
      while(rs.next()) {
        ProfessorBean bean = new ProfessorBean();
        bean.setpID(rs.getInt("PID"));
        bean.setpName(rs.getString("PName"));
        list.add(bean);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return list;
  }

  public static MajorBean getMajorByCode(String code) {
    MajorBean bean = new MajorBean();
    Connection conn = DBConnection.getConnection();
    String sql = "select MajorCode, MajorName from MAJOR where MajorCode = ?";
    PreparedStatement pstmt;
    ResultSet rs;
    try {
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, code);
      rs = pstmt.executeQuery();
      while(rs.next()) {
        bean.setMajorCode(rs.getString("MajorCode"));
        bean.setMajorName(rs.getString("MajorName"));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return bean;
  }

  public static List<MajorBean> getAllMajor() {
    List<MajorBean> list = new ArrayList<MajorBean>();
    Connection conn = DBConnection.getConnection();
    String sql = "select MajorCode, MajorName from MAJOR";
    PreparedStatement pstmt;
    ResultSet rs;
    try {
      pstmt = conn.prepareStatement(sql);
      rs = pstmt.executeQuery();
      while(rs.next()) {
        MajorBean bean = new MajorBean();
        bean.setMajorCode(rs.getString("MajorCode"));
        bean.setMajorName(rs.getString("MajorName"));
        list.add(bean);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return list;
  }

  public static List<BookBean> getBookByUCID(String UCID) {
    List<BookBean> list = new ArrayList<BookBean>();
    Connection conn = DBConnection.getConnection();
    String sql = "select * from BOOKS where UCID = ?";
    PreparedStatement pstmt;
    ResultSet rs;
    try {
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, UCID);
      rs = pstmt.executeQuery();
      while(rs.next()) {
        BookBean bean = new BookBean();
        bean.setId(rs.getInt("id"));
        bean.setUCID(rs.getString("UCID"));
        bean.setBookName(rs.getString("BookName"));
        bean.setMajor(rs.getString("Major"));
        bean.setCourseLevel(rs.getString("CourseLevel"));
        bean.setProfessor(rs.getString("Professor"));
        bean.setAddDate(rs.getString("AddDate"));
        bean.setSharedTimes(rs.getInt("SharedTimes"));
        bean.setPrivateOrShare(rs.getInt("PrivateOrShare"));
        bean.setFilePath(rs.getString("FilePath"));
        bean.setPhyPath(rs.getString("PhyPath"));
        list.add(bean);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return list;
  }

  public static BookBean getBookByID(int id) {
    BookBean bean = new BookBean();
    if(id != 0) {
      Connection conn = DBConnection.getConnection();
      String sql = "select * from BOOKS where id = ?";
      PreparedStatement pstmt;
      ResultSet rs;
      try {
        pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, id);
        rs = pstmt.executeQuery();
        while(rs.next()) {
          bean.setId(rs.getInt("id"));
          bean.setUCID(rs.getString("UCID"));
          bean.setBookName(rs.getString("BookName"));
          bean.setMajor(rs.getString("Major"));
          bean.setCourseLevel(rs.getString("CourseLevel"));
          bean.setProfessor(rs.getString("Professor"));
          bean.setAddDate(rs.getString("AddDate"));
          bean.setSharedTimes(rs.getInt("SharedTimes"));
          bean.setPrivateOrShare(rs.getInt("PrivateOrShare"));
          bean.setFilePath(rs.getString("FilePath"));
          bean.setPhyPath(rs.getString("PhyPath"));
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    return bean;
  }

  public static BookBean getBookByNameAndUCID(String bookName, String UCID) {
    BookBean bean = new BookBean();
    if(!bookName.equals("") && !UCID.equals("")) {
      Connection conn = DBConnection.getConnection();
      String sql = "select * from BOOKS where BookName = ? and UCID = ?";
      PreparedStatement pstmt;
      ResultSet rs;
      try {
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, bookName);
        pstmt.setString(2, UCID);
        rs = pstmt.executeQuery();
        while(rs.next()) {
          bean.setId(rs.getInt("id"));
          bean.setUCID(rs.getString("UCID"));
          bean.setBookName(rs.getString("BookName"));
          bean.setMajor(rs.getString("Major"));
          bean.setCourseLevel(rs.getString("CourseLevel"));
          bean.setProfessor(rs.getString("Professor"));
          bean.setAddDate(rs.getString("AddDate"));
          bean.setSharedTimes(rs.getInt("SharedTimes"));
          bean.setPrivateOrShare(rs.getInt("PrivateOrShare"));
          bean.setFilePath(rs.getString("FilePath"));
          bean.setPhyPath(rs.getString("PhyPath"));
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    return bean;
  }

  public static List<BookBean> getBookByName(String bookName) {
    List<BookBean> list = new ArrayList<BookBean>();
    if(!bookName.equals("")) {
      Connection conn = DBConnection.getConnection();
      String sql = "select * from BOOKS where BookName like ?";
      PreparedStatement pstmt;
      ResultSet rs;
      try {
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, "%"+bookName+"%");
        rs = pstmt.executeQuery();
        while(rs.next()) {
          BookBean bean = new BookBean();
          bean.setId(rs.getInt("id"));
          bean.setUCID(rs.getString("UCID"));
          bean.setBookName(rs.getString("BookName"));
          bean.setMajor(rs.getString("Major"));
          bean.setCourseLevel(rs.getString("CourseLevel"));
          bean.setProfessor(rs.getString("Professor"));
          bean.setAddDate(rs.getString("AddDate"));
          bean.setSharedTimes(rs.getInt("SharedTimes"));
          bean.setPrivateOrShare(rs.getInt("PrivateOrShare"));
          bean.setFilePath(rs.getString("FilePath"));
          bean.setPhyPath(rs.getString("PhyPath"));
          list.add(bean);
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    return list;
  }

  public static List<BookBean> getBookByProfessor(String professor) {
    List<BookBean> list = new ArrayList<BookBean>();
    if(!professor.equals("")) {
      Connection conn = DBConnection.getConnection();
      String sql = "select * from BOOKS where professor = ?";
      PreparedStatement pstmt;
      ResultSet rs;
      try {
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, professor);
        rs = pstmt.executeQuery();
        while(rs.next()) {
          BookBean bean = new BookBean();
          bean.setId(rs.getInt("id"));
          bean.setUCID(rs.getString("UCID"));
          bean.setBookName(rs.getString("BookName"));
          bean.setMajor(rs.getString("Major"));
          bean.setCourseLevel(rs.getString("CourseLevel"));
          bean.setProfessor(rs.getString("Professor"));
          bean.setAddDate(rs.getString("AddDate"));
          bean.setSharedTimes(rs.getInt("SharedTimes"));
          bean.setPrivateOrShare(rs.getInt("PrivateOrShare"));
          bean.setFilePath(rs.getString("FilePath"));
          bean.setPhyPath(rs.getString("PhyPath"));
          list.add(bean);
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    return list;
  }

  public static List<BookBean> getBookByCourseLevel(String courseLevel) {
    List<BookBean> list = new ArrayList<BookBean>();
    if(!courseLevel.equals("")) {
      Connection conn = DBConnection.getConnection();
      String sql = "select * from BOOKS where courseLevel = ?";
      PreparedStatement pstmt;
      ResultSet rs;
      try {
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, courseLevel);
        rs = pstmt.executeQuery();
        while(rs.next()) {
          BookBean bean = new BookBean();
          bean.setId(rs.getInt("id"));
          bean.setUCID(rs.getString("UCID"));
          bean.setBookName(rs.getString("BookName"));
          bean.setMajor(rs.getString("Major"));
          bean.setCourseLevel(rs.getString("CourseLevel"));
          bean.setProfessor(rs.getString("Professor"));
          bean.setAddDate(rs.getString("AddDate"));
          bean.setSharedTimes(rs.getInt("SharedTimes"));
          bean.setPrivateOrShare(rs.getInt("PrivateOrShare"));
          bean.setFilePath(rs.getString("FilePath"));
          bean.setPhyPath(rs.getString("PhyPath"));
          list.add(bean);
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    return list;
  }

  public static List<BookBean> getBookByMajor(String major) {
    List<BookBean> list = new ArrayList<BookBean>();
    if(!major.equals("")) {
      Connection conn = DBConnection.getConnection();
      String sql = "select * from BOOKS where major = ?";
      PreparedStatement pstmt;
      ResultSet rs;
      try {
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, major);
        rs = pstmt.executeQuery();
        while(rs.next()) {
          BookBean bean = new BookBean();
          bean.setId(rs.getInt("id"));
          bean.setUCID(rs.getString("UCID"));
          bean.setBookName(rs.getString("BookName"));
          bean.setMajor(rs.getString("Major"));
          bean.setCourseLevel(rs.getString("CourseLevel"));
          bean.setProfessor(rs.getString("Professor"));
          bean.setAddDate(rs.getString("AddDate"));
          bean.setSharedTimes(rs.getInt("SharedTimes"));
          bean.setPrivateOrShare(rs.getInt("PrivateOrShare"));
          bean.setFilePath(rs.getString("FilePath"));
          bean.setPhyPath(rs.getString("PhyPath"));
          list.add(bean);
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    return list;
  }

  public static List<BookBean> getAllBooks() {
    List<BookBean> list = new ArrayList<BookBean>();
    Connection conn = DBConnection.getConnection();
    String sql = "select * from BOOKS";
    PreparedStatement pstmt;
    ResultSet rs;
    try {
      pstmt = conn.prepareStatement(sql);
      rs = pstmt.executeQuery();
      while(rs.next()) {
        BookBean bean = new BookBean();
        bean.setId(rs.getInt("id"));
        bean.setUCID(rs.getString("UCID"));
        bean.setBookName(rs.getString("BookName"));
        bean.setMajor(rs.getString("Major"));
        bean.setCourseLevel(rs.getString("CourseLevel"));
        bean.setProfessor(rs.getString("Professor"));
        bean.setAddDate(rs.getString("AddDate"));
        bean.setSharedTimes(rs.getInt("SharedTimes"));
        bean.setPrivateOrShare(rs.getInt("PrivateOrShare"));
        bean.setFilePath(rs.getString("FilePath"));
        bean.setPhyPath(rs.getString("PhyPath"));
        list.add(bean);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return list;
  }

  public static List<BookListBean> getAllBooksForList(String except) {
    List<BookListBean> list = new ArrayList<BookListBean>();
      Connection conn = DBConnection.getConnection();
      String sql = "select * from BOOKS where UCID != ? and PrivateOrShare = 1";
      PreparedStatement pstmt;
      ResultSet rs;
      try {
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, except);
        rs = pstmt.executeQuery();
        while (rs.next()) {
          BookListBean bean = new BookListBean();
          bean.setId(rs.getInt("id"));
          bean.setUCID(rs.getString("UCID"));
          bean.setBookName(rs.getString("BookName"));
          bean.setMajor(rs.getString("Major"));
          bean.setCourseLevel(rs.getString("CourseLevel"));
          bean.setProfessor(rs.getString("Professor"));
          bean.setAddDate(rs.getString("AddDate"));
          bean.setSharedTimes(rs.getInt("SharedTimes"));
          bean.setHtmlButton();
          list.add(bean);
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    return list;
  }

  public static List<PrintBookBean> getPrintBookByNameAndUCID(String bookName, String UCID) {
    List<PrintBookBean> list = new ArrayList<PrintBookBean>();
    Connection conn = DBConnection.getConnection();
    String sql = "select * from PRINTBOOK where BookName=? and UCID=?";
    PreparedStatement pstmt;
    ResultSet rs;
    try {
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, bookName);
      pstmt.setString(2, UCID);
      rs = pstmt.executeQuery();
      while(rs.next()) {
        PrintBookBean bean = new PrintBookBean();
        bean.setUCID(rs.getString("UCID"));
        bean.setBookName(rs.getString("BookName"));
        bean.setPhyPath(rs.getString("PhyPath"));
        bean.setFilePath(rs.getString("FilePath"));
        bean.setIsPrinted(rs.getInt("IsPrinted"));
        list.add(bean);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return list;
  }


  public static void main(String args[]) {
    List<BookBean> list;

  }

}
