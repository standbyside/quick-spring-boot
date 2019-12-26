package quick.boot.drools.controller;

import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import quick.boot.drools.entity.Address;
import quick.boot.drools.entity.BooleanResult;
import quick.boot.drools.entity.Ming;
import quick.boot.drools.entity.OutputDisplay;
import quick.boot.drools.service.ReloadDroolsRulesService;

@RestController
public class TestController {

  @Autowired
  private ReloadDroolsRulesService rules;

  @GetMapping("/address/{num}")
  public Object address(@PathVariable int num) {
    Address address = new Address();
    address.setPostcode(generateRandom(num));
    KieSession kieSession = rules.kieContainer.newKieSession();
    kieSession.setGlobal("showResult", new OutputDisplay());

    BooleanResult result = new BooleanResult();
    kieSession.insert(address);
    kieSession.insert(result);
    int ruleFiredCount = kieSession.fireAllRules();
    kieSession.destroy();
    System.out.println("触发了" + ruleFiredCount + "条规则");

    return result;
  }

  @GetMapping("/ming/{money}")
  public Object ming(@PathVariable int money) {
    Ming ming = new Ming();
    ming.setMoney(money);

    KieSession kieSession = rules.kieContainer.newKieSession();
    kieSession.insert(ming);
    kieSession.fireAllRules();
    kieSession.destroy();

    System.out.println("总共喝掉：" + ming.getDrink());
    return ming;
  }

  /**
   * 从数据加载最新规则.
   */
  @GetMapping("/reload")
  public String reload() {
    rules.reload();
    return "ok";
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