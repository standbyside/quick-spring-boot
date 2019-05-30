package quick.boot.easypoi.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.google.common.collect.Lists;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import quick.boot.easypoi.entity.Department;
import quick.boot.easypoi.entity.Student;
import quick.boot.easypoi.entity.Teacher;
import quick.boot.easypoi.utils.DataUtils;
import quick.boot.easypoi.utils.ExcelUtils;


@Slf4j
@RestController
public class TestController {

  /**
   * 单sheet页，简单表头.
   */
  @GetMapping("test1")
  public ResponseEntity test1(HttpServletRequest request, HttpServletResponse response)
      throws IOException {

    List<Student> students = DataUtils.getStudents();

    Workbook wb = ExcelExportUtil.exportExcel(
        new ExportParams(null, "学生信息列表"), Student.class, students
    );
    String fileName = "test1_" + LocalDate.now().toString() + ".xlsx";
    ExcelUtils.writeExcel(request, response, wb, fileName);

    return null;
  }

  /**
   * 多sheet页.
   */
  @GetMapping("test2")
  public ResponseEntity test2(HttpServletRequest request, HttpServletResponse response)
      throws IOException {

    List<Student> students = DataUtils.getStudents();
    List<Teacher> teachers = DataUtils.getTeachers();


    // 创建参数对象（学生）
    ExportParams studentExportParam = new ExportParams();
    studentExportParam.setSheetName("学生报表");
    Map<String, Object> studentExportMap = new HashMap(16) {
      {
        put("title", studentExportParam);
        put("entity", Student.class);
        put("data", students);
      }
    };

    // 创建参数对象（教师）
    ExportParams teacherExportParam = new ExportParams();
    teacherExportParam.setSheetName("教师报表");
    Map<String, Object> teacherExportMap = new HashMap(16) {
      {
        put("title", teacherExportParam);
        put("entity", Teacher.class);
        put("data", teachers);
      }
    };

    List<Map<String, Object>> sheets = Lists.newArrayList(
        studentExportMap, teacherExportMap
    );

    Workbook wb = ExcelExportUtil.exportExcel(sheets, ExcelType.HSSF);
    String fileName = "test2_" + LocalDate.now().toString() + ".xlsx";

    ExcelUtils.writeExcel(request, response, wb, fileName);

    return null;
  }

  /**
   * 合并表头.
   */
  @GetMapping("test3")
  public ResponseEntity test3(HttpServletRequest request, HttpServletResponse response)
      throws IOException {

    List<Department> departments = DataUtils.getDeparments();

    Workbook wb = ExcelExportUtil.exportExcel(
        new ExportParams(null, "部门信息列表"), Department.class, departments
    );
    String fileName = "test3_" + LocalDate.now().toString() + ".xlsx";
    ExcelUtils.writeExcel(request, response, wb, fileName);

    return null;
  }
}
