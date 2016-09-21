package servlet;

import action.Book;
import action.GetInfo;
import bean.BookBean;
import util.GetTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by zhaokaihao on 12/6/15.
 */
@WebServlet(name = "SaveBookServlet")
public class SaveBookServlet extends HttpServlet {
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession(true);
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    int id = Integer.parseInt(request.getParameter("id"));
    System.out.println("This is saveBookServlet: " + id);
    BookBean bean1 = GetInfo.getBookByID(id);
    BookBean bean2 = new BookBean();
    bean2.setBookName(bean1.getBookName());
    bean2.setCourseLevel(bean1.getCourseLevel());
    System.out.println("adasd :" + bean1.getMajor());
    bean2.setMajor(bean1.getMajor());
    bean2.setFilePath(bean1.getFilePath());
    bean2.setPhyPath(bean1.getPhyPath());
    bean2.setProfessor(bean1.getProfessor());
    bean2.setAddDate(GetTime.getTime());
    bean2.setUCID((String)session.getAttribute("UCID"));
    bean2.setSharedTimes(bean1.getSharedTimes());
    boolean flag = Book.addBook(bean2);
    if(flag)
      out.write("success");
    else
      out.write("err");
    out.flush();
    out.close();
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

  }
}
