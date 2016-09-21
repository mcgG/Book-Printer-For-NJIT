package servlet;

import action.Check;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by zhaokaihao on 12/2/15.
 */
public class ValidLoginServlet extends javax.servlet.http.HttpServlet {
  protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
    PrintWriter out = response.getWriter();
    String UCID = request.getParameter("UCID");
    String password = request.getParameter("password");

    if(!UCID.equals("") && !password.equals("")) {
      Check check = new Check();
      boolean flag = check.checkPassword(UCID, password);
      if(flag) {
        HttpSession session = request.getSession();
        session.setAttribute("UCID", UCID);
        out.print("ok");
        response.sendRedirect("../home.jsp");
        System.out.println("Success!");
      } else {
        out.print("existed");
        System.out.println("failed to register! username or ucid is existed");
      }
    } else {
      out.print("err");
      System.out.println("failed to register!");
    }
    out.flush();
    out.close();
  }

  protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

  }
}
