package quick.boot.easypoi.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Emplyee {

  @Excel(name = "序号", width = 30, isColumnHidden = true)
  private Integer id;
  @Excel(name = "员工姓名", width = 30, groupName = "基本信息")
  private String name;
  @Excel(name = "年龄", width = 30, type = 10, groupName = "基本信息")
  private Integer age;
  @Excel(name = "入职时间", width = 30, groupName = "工作信息", format = "yyyy/MM/dd HH:mm")
  private LocalDate hireDate;
  @Excel(name = "薪酬", width = 30, type = 10, groupName = "工作信息")
  private BigDecimal salary;
}
