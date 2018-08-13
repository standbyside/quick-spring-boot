package com.zn.quick.spring.boot.spark.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zhaona
 * @create 2018/8/13 下午4:21
 */
@Data
public class Character implements Serializable {

  private String name;
  private Integer age;
}
