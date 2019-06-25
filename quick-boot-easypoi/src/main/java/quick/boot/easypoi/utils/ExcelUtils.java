package quick.boot.easypoi.utils;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Workbook;

public class ExcelUtils {

  /**
   * 写出Excel.
   *
   * @param response 响应
   * @param workbook excel
   * @param fileName 文件名
   */
  public static void writeExcel(HttpServletRequest request, HttpServletResponse response,
                                Workbook workbook, String fileName) throws IOException {
    String agent = request.getHeader("USER-AGENT");
    if (agent != null && agent.indexOf("Firefox") != -1) {
      // 火狐
      fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
    } else {
      // 其他
      fileName = URLEncoder.encode(fileName, "UTF-8");
    }
    // 重置响应对象
    response.reset();
    // 指定下载的文件名--设置响应头
    response.setHeader("content-Type", "application/vnd.ms-excel");
    response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
    // 写出数据输出流到页面
    OutputStream os = response.getOutputStream();
    BufferedOutputStream bos = new BufferedOutputStream(os);
    workbook.write(bos);
    bos.flush();
    bos.close();
    os.close();
  }

  /**
   * 写出Excel到本地.
   */
  public static void writeLocal(Workbook wb, String fileName) throws IOException {
    FileOutputStream fos = new FileOutputStream("/Users/zn/Downloads/" + fileName);
    wb.write(fos);
    fos.close();
  }
}
