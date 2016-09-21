package servlet;

import action.Book;
import action.GetInfo;
import bean.BookBean;
import com.jspsmart.upload.*;
import util.GetTime;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.io.File;

/**
 * Created by zhaokaihao on 12/4/15.
 */
public class FileUploadServlet extends HttpServlet {
  String path = null;
  boolean flag = false;
  BookBean bean = new BookBean();
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      request.setCharacterEncoding("utf-8");
      response.setCharacterEncoding("utf-8");
      HttpSession session = request.getSession();
      SmartUpload su = new SmartUpload();

      su.initialize(this.getServletConfig(), request, response);
      String allowed = "pdf";
      su.setAllowedFilesList(allowed);
      try{
        su.upload();
        path = request.getSession().getServletContext().
            getRealPath(File.separator + "uploadFiles");
        System.out.println("path: " + path);
        File dir = new File(path);
        if(!dir.exists()){
          dir.mkdir();
          System.out.println(dir.toString() + " created");
        }else{
          System.out.println(dir.toString() + " is exited");
        }

        System.out.println("files: " + su.getFiles().getCount());
        for(int i=0; i<su.getFiles().getCount(); i++){
          com.jspsmart.upload.File file = su.getFiles().getFile(i);
          if(file.isMissing())
            continue;
          String bookName = new String(su.getRequest().getParameter("bookName").getBytes());
          String UCID = session.getAttribute("UCID").toString();
          String major = new String(su.getRequest().getParameter("major").getBytes());
          String courseLevel = new String(su.getRequest().getParameter("courseLevel").getBytes());
          int profID = Integer.parseInt(new String(su.getRequest().getParameter("professor").getBytes()));
          String professor = GetInfo.getProfessorByID(profID);
          String addDate = GetTime.getTime();
          String phyFilePath = path + File.separator + new String(file.getFileName().getBytes());
          String relativePath = File.separator + "uploadFiles" + File.separator + new String(file.getFileName().getBytes());

          System.out.println("UCID: " + UCID);
          System.out.println("relativePath: " + relativePath);

          file.saveAs(phyFilePath, SmartUpload.SAVE_PHYSICAL);
          bean.setBookName(bookName);
          bean.setUCID(UCID);
          bean.setMajor(GetInfo.getMajorByCode(major).getMajorName());
          bean.setCourseLevel(courseLevel);
          bean.setProfessor(professor);
          bean.setAddDate(addDate);
          bean.setFilePath(relativePath);
          bean.setPhyPath(phyFilePath);

          flag = Book.addBook(bean);

        }
      }catch(SmartUploadException e){
        e.printStackTrace();
        out.write("error");
      }
    if(!flag)
        out.write("error");
      else {
      out.write("Success");
      response.sendRedirect("../home.jsp");
    }
      out.flush();
      out.close();
    }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

  }
}
