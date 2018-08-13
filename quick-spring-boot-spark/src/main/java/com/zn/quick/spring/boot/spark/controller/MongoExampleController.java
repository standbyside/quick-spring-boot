package com.zn.quick.spring.boot.spark.controller;

import com.zn.quick.spring.boot.spark.service.MongoExampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhaona
 * @create 2018/8/13 下午4:04
 */
@RestController
@RequestMapping("/mongo")
public class MongoExampleController {

  @Autowired
  private MongoExampleService mongoExampleService;

  @GetMapping("/write")
  public Object write() {
    mongoExampleService.write();
    return "success";
  }

  @GetMapping("/read")
  public Object read() {
    return mongoExampleService.read();
  }

  @GetMapping("/aggregation")
  public Object aggregation() {
    return mongoExampleService.aggregation();
  }

  @GetMapping("/dataset")
  public Object dataset() {
    return mongoExampleService.dataset();
  }
}
