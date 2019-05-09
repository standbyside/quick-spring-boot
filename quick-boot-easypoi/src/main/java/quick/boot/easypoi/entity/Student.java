package quick.boot.easypoi.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Student {

  /**
   * 学号.
   */
  private String no;
  /**
   * 姓名.
   */
  private String name;
  /**
   * 性别.
   */
  private Gender gender;
  /**
   * 年龄.
   */
  private Integer age;
  /**
   * 所属班级.
   */
  private String classes;


  public enum Gender {

    MALE("男"), FEMALE("女");

    private String description;

    Gender(String description) {
      this.description = description;
    }

    public String description() {
      return description;
    }

  }
}
