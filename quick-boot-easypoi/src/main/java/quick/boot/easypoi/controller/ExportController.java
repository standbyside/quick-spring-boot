package quick.boot.easypoi.controller;

import java.io.IOException;
import java.time.LocalDate;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import quick.boot.easypoi.service.ExportService;
import quick.boot.easypoi.utils.ExcelUtils;


@Slf4j
@RequestMapping("/export")
@RestController
public class ExportController {

  @Autowired
  private ExportService exportService;

  @GetMapping("test")
  public ResponseEntity test1(HttpServletRequest request, HttpServletResponse response)
      throws IOException {

    Workbook wb = exportService.test1();
    String fileName = "test1_" + LocalDate.now().toString() + ".xls";
    ExcelUtils.writeExcel(request, response, wb, fileName);

    return null;
  }
}
