package servlet;

import action.Book;
import action.GetInfo;
import bean.BookBean;

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
@WebServlet(name = "ShareBookServlet")
public class ShareBookServlet extends HttpServlet {
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    HttpSession session = request.getSession(true);
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    String bookID = request.getParameter("id");
    BookBean bean = GetInfo.getBookByID(Integer.parseInt(bookID));
    String UCID = (String)session.getAttribute("UCID");
    boolean flag = Book.shareBook(bean.getBookName(), UCID);
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
