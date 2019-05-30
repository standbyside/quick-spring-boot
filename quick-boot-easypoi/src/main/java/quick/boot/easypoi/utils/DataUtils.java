package quick.boot.easypoi.utils;

import com.google.common.collect.Lists;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import quick.boot.easypoi.entity.Department;
import quick.boot.easypoi.entity.Emplyee;
import quick.boot.easypoi.entity.Student;
import quick.boot.easypoi.entity.Teacher;
import quick.boot.easypoi.enums.Gender;


public class DataUtils {

  public static List<Student> getStudents() {
    return Lists.newArrayList(
        Student.builder().studentId(1).name("1号学生").gender(Gender.MALE).classes("一年一班")
            .createTime(LocalDate.of(2019, 1, 1)).build(),
        Student.builder().studentId(2).name("2号学生").gender(Gender.FEMALE).classes("一年一班")
            .createTime(LocalDate.of(2019, 1, 1)).build(),
        Student.builder().studentId(3).name("3号学生").gender(Gender.MALE).classes("一年二班")
            .createTime(LocalDate.of(2019, 1, 1)).build()
    );
  }

  public static List<Teacher> getTeachers() {
    return Lists.newArrayList(
        Teacher.builder().workId(1).name("1号教师").gender(Gender.MALE).subject("语文")
            .createTime(LocalDate.of(2019, 1, 1)).build(),
        Teacher.builder().workId(2).name("2号教师").gender(Gender.FEMALE).subject("数学")
            .createTime(LocalDate.of(2019, 1, 1)).build(),
        Teacher.builder().workId(3).name("3号教师").gender(Gender.MALE).subject("英语")
            .createTime(LocalDate.of(2019, 1, 1)).build()
    );
  }

  public static List<Department> getDeparments() {
    return Lists.newArrayList(
        Department.builder()
            .id(1).name("1号部门")
            .emplyees(
              Lists.newArrayList(
                  Emplyee.builder().id(1).name("1号员工").age(26).hireDate(LocalDate.now())
                      .salary(BigDecimal.valueOf(3000)).build(),
                  Emplyee.builder().id(2).name("2号员工").age(32).hireDate(LocalDate.now())
                      .salary(BigDecimal.valueOf(5000)).build()
              )
            ).build(),
        Department.builder()
            .id(2).name("2号部门")
            .emplyees(
                Lists.newArrayList(
                    Emplyee.builder().id(3).name("3号员工").age(21).hireDate(LocalDate.now())
                        .salary(BigDecimal.valueOf(2500)).build(),
                    Emplyee.builder().id(4).name("4号员工").age(22).hireDate(LocalDate.now())
                        .salary(BigDecimal.valueOf(2600)).build()
                )
            ).build()
    );
  }
}
