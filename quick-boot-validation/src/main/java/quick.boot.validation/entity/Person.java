package quick.boot.validation.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.ScriptAssert;
import quick.boot.validation.common.anno.IsMobile;
import quick.boot.validation.common.anno.Test1;
import quick.boot.validation.common.anno.Test2;


@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@ScriptAssert.List({
    @ScriptAssert(lang = "javascript", script = "_this.startDate <= _this.endDate",
        message = "开始时间不能大于结束时间")
})
public class Person {

  @NotNull(message = "id不能为空", groups = {Test1.class})
  private Long id;

  @NotEmpty(message = "姓名不能为空", groups = {Test2.class})
  private String name;

  @NotNull(message = "年龄不能为空", groups = {Test1.class, Test2.class})
  private Integer age;

  @NotNull(message = "地址不能为空", groups = {Test1.class})
  @Valid
  private Address address;

  @IsMobile
  private String mobile;

  @NotNull(message = "数据有效期开始时间不能为空")
  @JsonFormat(pattern = "yyyy-MM-dd")
  private LocalDate startDate;

  @JsonFormat(pattern = "yyyy-MM-dd")
  @NotNull(message = "数据有效期结束时间不能为空")
  private LocalDate endDate;
}
