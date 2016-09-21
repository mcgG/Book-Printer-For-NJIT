package util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by zhaokaihao on 12/5/15.
 */
public class AddMajor {

  static int flag = 0;

  public static void main(String [] args) {

    String fileName = "major.txt";
    String line = null;

    Connection conn = DBConnection.getConnection();
    String sql = "insert into MAJOR (MajorCode, MajorName) values (?, ?)";
    PreparedStatement pstmt;
    try {
      FileReader fileReader = new FileReader(fileName);
      BufferedReader bufferedReader = new BufferedReader(fileReader);
      while((line = bufferedReader.readLine()) != null) {
        String majorCode = line.split("\"")[1];
        String majorName = line.split(">")[1].split("<")[0];
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, majorCode);
        pstmt.setString(2, majorName);
        flag = pstmt.executeUpdate();
      }
      bufferedReader.close();
    }
    catch(FileNotFoundException ex) {
      System.out.println("Unable to open file '" + fileName + "'");
    }
    catch(IOException ex) {
      System.out.println("Error reading file '" + fileName + "'");
    } catch (SQLException e) {
      e.printStackTrace();
    }

    if(flag != 0) {
      System.out.println("Success");
    }
  }
}
