package action;

import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by zhaokaihao on 12/2/15.
 */
public class Check {

  public static boolean checkUserName(String username, String UCID) {
    if(!username.equals("") && !UCID.equals("")) {
      Connection conn = DBConnection.getConnection();
      String sql = "select UCID from USERS where UCID=? and UserName=?";
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      try {
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, UCID);
        pstmt.setString(2, username);
        rs = pstmt.executeQuery();
        if(!rs.next()) {
          System.out.println("Success!");
          return true;
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }

    }
    System.out.println("Failed this username has existed!");
    return false;
  }

  public static boolean checkPassword(String UCID, String password) {
    if(!password.equals("") && !UCID.equals("")) {
      Connection conn = DBConnection.getConnection();
      String sql = "select UCID from USERS where UCID=? and Password=?";
      PreparedStatement pstmt;
      ResultSet rs;
      try {
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, UCID);
        pstmt.setString(2, password);
        rs = pstmt.executeQuery();
        if(rs.next()) {
          System.out.println("Success!");
          return true;
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }

    }
    System.out.println("Failed wrong password!");
    return false;
  }

  public static boolean checkBookAvailable(String bookName, String UCID) {
    if(!bookName.equals("") && !UCID.equals("")) {
      Connection conn = DBConnection.getConnection();
      String sql = "select UCID from BOOKS where UCID=? and BookName=?";
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      try {
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, UCID);
        pstmt.setString(2, bookName);
        rs = pstmt.executeQuery();
        if(!rs.next()) {
          System.out.println("Success!");
          return true;
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    System.out.println("Failed this book has existed!");
    return false;
  }

  public static void main(String args[]) {
    System.out.println(checkUserName("Kaihao", "kz82"));
    System.out.println(checkBookAvailable("Java", "kz82"));
  }

}
