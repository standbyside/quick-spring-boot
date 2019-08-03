package quick.boot.validation.entity;

import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import quick.boot.validation.common.anno.Test1;


@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Address {

  @NotEmpty(message = "省名称不能为空", groups = {Test1.class})
  private String province;

  @NotEmpty(message = "城市名称不能为空")
  private String city;

  @NotEmpty(message = "住址名称不能为空")
  private String location;
}
