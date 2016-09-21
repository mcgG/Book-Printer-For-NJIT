package servlet;

import action.Register;
import bean.UserInfoBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by zhaokaihao on 12/7/15.
 */
@WebServlet(name = "RegisterServlet")
public class RegisterServlet extends HttpServlet {
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    String UCID = request.getParameter("UCID");
    String password = request.getParameter("password");
    String userName = request.getParameter("username");
    String major = request.getParameter("major");
    UserInfoBean bean = new UserInfoBean();
    bean.setUserName(userName);
    bean.setPassword(password);
    bean.setMajor(major);
    bean.setUCID(UCID);
    boolean flag = Register.register(bean);
    if(flag) {
      HttpSession session = request.getSession();
      session.setAttribute("UCID", UCID);
      response.sendRedirect("../home.jsp");
    } else {
      response.sendRedirect("../index.jsp");
    }

  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

  }
}
