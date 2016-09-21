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
public class AddProfessor {

  static int flag = 0;

  public static void main(String [] args) {

    String fileName = "professor.txt";
    String line = null;

    Connection conn = DBConnection.getConnection();
    String sql = "insert into PROFESSORS (PID, PName) values (?, ?)";
    PreparedStatement pstmt;
    try {
      FileReader fileReader = new FileReader(fileName);
      BufferedReader bufferedReader = new BufferedReader(fileReader);
      while((line = bufferedReader.readLine()) != null) {
        String pid = line.split("\"")[1];
        String pName = line.split(">")[1];
        //System.out.println(pName);
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, pid);
        pstmt.setString(2, pName);
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
