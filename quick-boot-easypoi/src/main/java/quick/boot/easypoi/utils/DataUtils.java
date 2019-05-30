package quick.boot.easypoi.utils;

import com.google.common.collect.Lists;
import java.time.LocalDate;
import java.util.List;
import quick.boot.easypoi.entity.Student;
import quick.boot.easypoi.entity.Teacher;
import quick.boot.easypoi.enums.Gender;


public class DataUtils {

  public static List<Student> getStudentList() {
    return Lists.newArrayList(
        Student.builder().studentId(1).name("1号学生").gender(Gender.MALE).classes("一年一班")
            .createTime(LocalDate.of(2019, 1, 1)).build(),
        Student.builder().studentId(2).name("2号学生").gender(Gender.FEMALE).classes("一年一班")
            .createTime(LocalDate.of(2019, 1, 1)).build(),
        Student.builder().studentId(3).name("3号学生").gender(Gender.MALE).classes("一年二班")
            .createTime(LocalDate.of(2019, 1, 1)).build()
    );
  }

  public static List<Teacher> getTeacherList() {
    return Lists.newArrayList(
        Teacher.builder().workId(1).name("1号教师").gender(Gender.MALE).subject("语文")
            .createTime(LocalDate.of(2019, 1, 1)).build(),
        Teacher.builder().workId(2).name("2号教师").gender(Gender.FEMALE).subject("数学")
            .createTime(LocalDate.of(2019, 1, 1)).build(),
        Teacher.builder().workId(3).name("3号教师").gender(Gender.MALE).subject("英语")
            .createTime(LocalDate.of(2019, 1, 1)).build()
    );
  }
}
