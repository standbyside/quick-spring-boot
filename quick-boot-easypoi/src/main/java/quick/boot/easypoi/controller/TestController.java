package quick.boot.easypoi.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.google.common.collect.Lists;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import quick.boot.easypoi.entity.Student;
import quick.boot.easypoi.enums.Gender;
import quick.boot.easypoi.utils.ExcelUtils;


@Slf4j
@RestController
public class TestController {

  @GetMapping("test1")
  public ResponseEntity test1(HttpServletRequest request, HttpServletResponse response)
      throws IOException {

    List<Student> rows = getStudentList();

    Workbook wb = ExcelExportUtil.exportExcel(
        new ExportParams(null, "学生信息列表"), Student.class, rows
    );
    ExcelUtils.writeExcel(request, response, wb, "学生信息列表_"
        + LocalDate.now().toString() + ".xlsx");

    return null;
  }


  private List<Student> getStudentList() {
    return Lists.newArrayList(
        Student.builder().no(1).name("1号学生").gender(Gender.MALE).classes("一年一班")
            .createTime(LocalDate.of(2019, 1, 1)).build(),
        Student.builder().no(2).name("2号学生").gender(Gender.FEMALE).classes("一年一班")
            .createTime(LocalDate.of(2019, 1, 1)).build(),
        Student.builder().no(3).name("3号学生").gender(Gender.MALE).classes("一年二班")
            .createTime(LocalDate.of(2019, 1, 1)).build()
    );
  }
}
