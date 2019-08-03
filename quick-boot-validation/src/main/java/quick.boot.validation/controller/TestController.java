package quick.boot.validation.controller;

import java.util.List;
import javax.validation.groups.Default;
import org.hibernate.validator.constraints.ParameterScriptAssert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import quick.boot.validation.common.anno.Test1;
import quick.boot.validation.common.anno.Test2;
import quick.boot.validation.common.CommonResponse;
import quick.boot.validation.entity.Person;


@RestController
public class TestController {

  @PostMapping("/test1")
  public CommonResponse test1(@Validated({Test1.class, Default.class}) @RequestBody Person person) {
    return CommonResponse.ok(person);
  }

  @PostMapping("/test2")
  public CommonResponse test2(@Validated({Test2.class}) @RequestBody Person person) {
    return CommonResponse.ok(person);
  }

  @PostMapping("/test3")
  public CommonResponse test3(@Validated({Test1.class, Test2.class}) @RequestBody Person person) {
    return CommonResponse.ok(person);
  }

  @PostMapping("/test4")
  public CommonResponse test4(@Validated @RequestBody Person person) {
    return CommonResponse.ok(person);
  }

  @GetMapping("/test5")
  @Validated
  @ParameterScriptAssert(lang = "javascript", script = "arg0.size() == arg1.size()",
      message = "id和名字数量必须相等")
  public CommonResponse test5(@RequestParam("ids") List<Long> ids,
                              @RequestParam("names") List<String> names) {
    System.out.println(ids);
    System.out.println(names);
    return CommonResponse.ok();
  }
}
