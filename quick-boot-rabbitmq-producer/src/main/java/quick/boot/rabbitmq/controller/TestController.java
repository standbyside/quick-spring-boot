package quick.boot.rabbitmq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import quick.boot.rabbitmq.producer.*;

@RestController
@RequestMapping("/test")
public class TestController {

  @Autowired
  private DirectProducer directProducer;
  @Autowired
  private TopicProducer topicProducer;
  @Autowired
  private FanoutProducer fanoutProducer;
  @Autowired
  private ExceptionProducer exceptionProducer;
  @Autowired
  private DelayProducer delayProducer;
  @Autowired
  private NonexistentProducer nonexistentProducer;

  @GetMapping("/direct")
  public Object testDirect() {
    directProducer.send();
    return "test direct success";
  }

  @GetMapping("/topic/1")
  public Object testTopic1() {
    topicProducer.send1();
    return "test topic 1 success";
  }

  @GetMapping("/topic/2")
  public Object testTopic2() {
    topicProducer.send2();
    return "test topic 2 success";
  }

  @GetMapping("/fanout")
  public Object testFanout() {
    fanoutProducer.send();
    return "test fanout success";
  }

  @GetMapping("/exception")
  public Object testException() {
    exceptionProducer.send();
    return "test exception success";
  }

  @GetMapping("/delay")
  public Object testDelay() {
    delayProducer.send();
    return "test delay success";
  }

  @GetMapping("/nonexistant1")
  public Object testNonexistant1() {
    nonexistentProducer.send1();
    return "test nonexistant 1 success";
  }

  @GetMapping("/nonexistant2")
  public Object testNonexistant2() {
    nonexistentProducer.send2();
    return "test nonexistant 1 success";
  }

  @GetMapping("/nonexistant3")
  public Object testNonexistant3() {
    nonexistentProducer.send3();
    return "test nonexistant 3 success";
  }
}
