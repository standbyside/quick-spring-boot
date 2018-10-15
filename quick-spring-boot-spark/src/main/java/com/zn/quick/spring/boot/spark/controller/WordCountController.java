package com.zn.quick.spring.boot.spark.controller;

import com.zn.quick.spring.boot.spark.service.WordCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/word-count")
public class WordCountController {

  @Autowired
  private WordCountService wordCountService;

  @GetMapping("/run")
  public Object run() {
    return wordCountService.run();
  }
}
