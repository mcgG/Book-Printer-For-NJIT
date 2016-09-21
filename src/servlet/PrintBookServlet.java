package servlet;

import action.Book;
import action.GetInfo;
import action.PDFFile;
import bean.BookBean;
import bean.PrintBookBean;
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
 * Created by zhaokaihao on 12/6/15.
 */
@WebServlet(name = "PrintBookServlet")
public class PrintBookServlet extends HttpServlet {
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    HttpSession session = request.getSession(true);
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    int id = Integer.parseInt(request.getParameter("id"));
    BookBean bean = GetInfo.getBookByID(id);
    System.out.println("book info : " + bean.getBookName());
    List<PrintBookBean> list = GetInfo.getPrintBookByNameAndUCID(bean.getBookName(), bean.getUCID());
    for(int i=0; i<list.size(); i++) {
      list.get(i).setBookName(PDFFile.getFileName(list.get(i).getPhyPath()));
    }
    JSONArray json = JSONArray.fromObject(list);
    out.write(json.toString());
    System.out.println("list size: " + list.size() + "\t" + json.toString());
    out.flush();
    out.close();
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

  }
}
