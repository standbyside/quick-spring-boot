package quick.boot.easypoi.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import quick.boot.easypoi.enums.Gender;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Student {

  @Excel(name = "学号", orderNum = "1")
  private Integer studentId;

  @Excel(name = "姓名", orderNum = "2")
  private String name;

  @Excel(name = "年龄", orderNum = "3")
  private Integer age;

  @Excel(name = "性别", orderNum = "4", replace = {"男_MALE", "女_FEMALE"})
  private Gender gender;

  @Excel(name = "所属班级", orderNum = "5")
  private String classes;

  @Excel(name = "入学时间", orderNum = "6", width = 15, format = "yyyyMMdd")
  private LocalDate createTime;

}
