package action;

import bean.UserInfoBean;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by zhaokaihao on 12/2/15.
 */
public class Register {

  public static boolean register(UserInfoBean bean) {
    if(bean != null) {
      Connection conn = DBConnection.getConnection();
      String sql = "insert into USERS (UCID, UserName, Major, Password) values (?,?,?,?)";
      PreparedStatement pstmt = null;
      int i = 0;
      try {
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, bean.getUCID());
        pstmt.setString(2, bean.getUserName());
        pstmt.setString(3, bean.getMajor());
        pstmt.setString(4, bean.getPassword());
        i = pstmt.executeUpdate();
      } catch (SQLException e) {
        e.printStackTrace();
      }
      if(i != 0)
        return true;
    }
    return false;
  }

}
