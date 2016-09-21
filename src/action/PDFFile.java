package action;

import bean.BookBean;
import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfCopy;
import com.lowagie.text.pdf.PdfImportedPage;
import com.lowagie.text.pdf.PdfReader;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by zhaokaihao on 12/3/15.
 */
public class PDFFile {

  public static String getFileName(String path) {
    String[] pathArr = path.split(File.separator);
    return pathArr[pathArr.length-1];
  }

  public static List<BookBean> splitFile(BookBean selectBookBean) {
    System.out.println("hahahahahah");
    List<BookBean> list = new ArrayList<BookBean>();
    try {
      int pageStep = 25;
      String inFile = selectBookBean.getPhyPath();
      String inRelativeFile = selectBookBean.getFilePath();
      System.out.println("adfasfs: "+inFile + "\t" + inRelativeFile);
      PdfReader reader = new PdfReader(inFile);
      int n = reader.getNumberOfPages();
      int i = 0;
      int count = 0;
      int count2 = 0;
      int startPage=0, endPage = 0;
      int remainPage = n % pageStep;
      while ( i <= n / pageStep ) {
        BookBean bean = new BookBean();
        String outFile = inFile.substring(0, inFile.indexOf(".pdf"))
            + "-" + String.format("%03d", count++) + ".pdf";
        String outRelativeFile = inRelativeFile.substring(0, inRelativeFile.indexOf(".pdf"))
            + "-" + String.format("%03d", count2++) + ".pdf";
        bean.setPhyPath(outFile);
        bean.setFilePath(outRelativeFile);
        list.add(bean);
        Document document = new Document(reader.getPageSizeWithRotation(pageStep));
        PdfCopy writer = new PdfCopy(document, new FileOutputStream(outFile));
        document.open();
        startPage = i * pageStep +1;
        endPage = (i +1 ) * pageStep;
        System.out.println("startPage: " + startPage);
        System.out.println("endPage: " + endPage);
        if(remainPage != 0 && i == (n/pageStep)) {
          endPage = n;
        }
        for(int j=startPage; j<=endPage; j++) {
          PdfImportedPage page = writer.getImportedPage(reader, j);
          writer.addPage(page);
        }
        document.close();
        writer.close();
        i++;
      }
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

}
