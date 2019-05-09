package quick.boot.easypoi.enums;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Gender {

  MALE(1, "男"), FEMALE(2, "女");

  private Integer code;
  private String description;

  Gender(Integer code, String description) {
    this.code = code;
    this.description = description;
  }

  public String description() {
    return description;
  }

  public static List<String> nameDescriptionArray() {
    return Arrays.stream(Gender.values())
        .map(o -> o.name() + "_" + o.description)
        .collect(Collectors.toList());
  }
}
