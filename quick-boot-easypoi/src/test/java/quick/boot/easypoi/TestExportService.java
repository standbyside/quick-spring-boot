package quick.boot.easypoi;

import java.io.IOException;
import org.junit.Test;
import quick.boot.easypoi.service.ExportService;
import quick.boot.easypoi.utils.ExcelUtils;


public class TestExportService {

  private static final ExportService exportService = new ExportService();

  //@Test
  public void test1() throws IOException {
    ExcelUtils.writeLocal(exportService.test1(), "export_test_1.xls");
  }

  //@Test
  public void test2() throws IOException {
    ExcelUtils.writeLocal(exportService.test2(), "export_test_2.xls");
  }

  //@Test
  public void test3() throws IOException {
    ExcelUtils.writeLocal(exportService.test3(), "export_test_3.xls");
  }

  //@Test
  public void test4() throws IOException {
    ExcelUtils.writeLocal(exportService.test4(), "export_test_4.xls");
  }
}
