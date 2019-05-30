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
public class Teacher {

  @Excel(name = "工号", orderNum = "1")
  private Integer workId;

  @Excel(name = "姓名", orderNum = "2")
  private String name;

  @Excel(name = "性别", orderNum = "3", replace = {"男_MALE", "女_FEMALE"})
  private Gender gender;

  @Excel(name = "教授课程", orderNum = "4")
  private String subject;

  @Excel(name = "创建时间", orderNum = "5", width = 15, format = "yyyyMMdd")
  private LocalDate createTime;
}
