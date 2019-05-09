package quick.boot.spark.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Character implements Serializable {

  private String name;
  private Integer age;
}
