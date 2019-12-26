package quick.boot.drools.service;


import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieRepository;
import org.kie.api.builder.Message;
import org.kie.api.runtime.KieContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import quick.boot.drools.entity.Rule;
import quick.boot.drools.repository.RuleRepository;

import java.util.List;

@Service
public class ReloadDroolsRulesService {

  @Autowired
  public KieContainer kieContainer;

  @Autowired
  private RuleRepository ruleRepository;

  public void reload() {
    this.kieContainer = loadContainerFromString(loadRules());
  }

  private List<Rule> loadRules() {
    List<Rule> rules = ruleRepository.findAll();
    return rules;
  }

  private KieContainer loadContainerFromString(List<Rule> rules) {
    long startTime = System.currentTimeMillis();
    KieServices ks = KieServices.Factory.get();
    KieRepository kr = ks.getRepository();
    KieFileSystem kfs = ks.newKieFileSystem();

    for (Rule rule : rules) {
      String drl = rule.getContent();
      kfs.write("src/main/resources/" + Math.abs(drl.hashCode()) + ".drl", drl);
    }

    KieBuilder kb = ks.newKieBuilder(kfs);

    kb.buildAll();
    if (kb.getResults().hasMessages(Message.Level.ERROR)) {
      throw new RuntimeException("Build Errors:\n" + kb.getResults().toString());
    }
    long endTime = System.currentTimeMillis();
    System.out.println("Time to build rules: " + (endTime - startTime) + " ms");
    startTime = System.currentTimeMillis();
    KieContainer kContainer = ks.newKieContainer(kr.getDefaultReleaseId());
    endTime = System.currentTimeMillis();
    System.out.println("Time to load container: " + (endTime - startTime) + " ms");
    return kContainer;
  }
}