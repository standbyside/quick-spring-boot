package quick.boot.easypoi.service;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.google.common.collect.Lists;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;
import quick.boot.easypoi.entity.Department;
import quick.boot.easypoi.entity.Student;
import quick.boot.easypoi.entity.Teacher;
import quick.boot.easypoi.utils.DataUtils;

@Service
public class ExportService {

  /**
   * 单sheet页，简单表头.
   */
  public Workbook test1() {

    List<Student> students = DataUtils.getStudents();

    return ExcelExportUtil.exportExcel(
        new ExportParams(null, "学生信息列表"), Student.class, students
    );
  }

  /**
   * 多sheet页.
   */
  public Workbook test2() {

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

    return ExcelExportUtil.exportExcel(sheets, ExcelType.HSSF);
  }

  /**
   * 合并表头.
   */
  public Workbook test3() {

    List<Department> departments = DataUtils.getDepartments();

    return ExcelExportUtil.exportExcel(
        new ExportParams(null, "部门信息列表"), Department.class, departments
    );
  }

  /**
   * 模版导出.
   */
  public Workbook test4() {
    Map<String,Object> map = new HashMap<>(16);
    map.put("recordTime", LocalDate.now());
    map.put("teachers", DataUtils.getTeachers());
    map.put("students", DataUtils.getStudents());
    map.put("departments", DataUtils.getDepartments());

    // 标明Excel模版和Sheet页
    TemplateExportParams params = new TemplateExportParams(
        "templates/school-person-records-template.xls", 0, 1);

    return ExcelExportUtil.exportExcel(params, map);
  }
}
