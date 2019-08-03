package quick.boot.validation.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommonResponse<T> {

  private Integer status;

  private String message;

  private T data;

  public static <T> CommonResponse<T> ok(T data) {
    return new CommonResponse<>(data);
  }

  public static CommonResponse ok() {
    return new CommonResponse<>(null);
  }

  /**
   * constructor.
   */
  private CommonResponse(T data) {
    this.status = 200;
    this.message = "OK";
    this.data = data;
  }
}
