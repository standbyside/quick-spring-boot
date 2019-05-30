package quick.boot.easypoi.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelCollection;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Department {

  @Excel(name = "部门编号", width = 30 , needMerge = true)
  private Integer id;
  @Excel(name = "部门名称", width = 30 , needMerge = true)
  private String name;
  @ExcelCollection(name = "员工信息")
  private List<Emplyee> emplyees;
}
