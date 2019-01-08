package quick.spring.boot.rabbitmq.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Character implements Serializable {

  private String name;
  private Integer age;
}
