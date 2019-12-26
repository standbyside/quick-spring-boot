package quick.boot.drools.controller;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import quick.boot.drools.entity.Address;
import quick.boot.drools.entity.AddressCheckResult;

@RestController
public class TestController {

  @Autowired
  private KieContainer kieContainer;

  @GetMapping("/test/address/{num}")
  public void test(@PathVariable int num) {
    Address address = new Address();
    address.setPostcode(generateRandom(num));
    KieSession kieSession = kieContainer.newKieSession();

    AddressCheckResult result = new AddressCheckResult();
    kieSession.insert(address);
    kieSession.insert(result);
    int ruleFiredCount = kieSession.fireAllRules();
    kieSession.destroy();
    System.out.println("触发了" + ruleFiredCount + "条规则");

    if (result.isPostCodeResult()) {
      System.out.println("规则校验通过");
    }
  }

  /**
   * 生成随机数.
   */
  public String generateRandom(int num) {
    String chars = "0123456789";
    StringBuffer number = new StringBuffer();
    for (int i = 0; i < num; i++) {
      int rand = (int) (Math.random() * 10);
      number = number.append(chars.charAt(rand));
    }
    return number.toString();
  }
}