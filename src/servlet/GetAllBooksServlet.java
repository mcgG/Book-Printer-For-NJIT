package servlet;

import action.GetInfo;
import bean.BookBean;
import bean.BookListBean;
import net.sf.json.JSONArray;

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
 * Created by zhaokaihao on 12/7/15.
 */
@WebServlet(name = "GetAllBooksServlet")
public class GetAllBooksServlet extends HttpServlet {
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    HttpSession session = request.getSession(true);
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    System.out.println("111111"+(String)session.getAttribute("UCID"));
    List<BookListBean> list = GetInfo.getAllBooksForList((String) session.getAttribute("UCID"));
    if(list != null){
      JSONArray json = JSONArray.fromObject(list);
      out.write(json.toString());
    } else {
      out.write("err");
    }
    out.flush();
    out.close();
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

  }
}
