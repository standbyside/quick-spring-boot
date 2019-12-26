package quick.boot.drools.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import quick.boot.drools.entity.Rule;

public interface RuleRepository extends JpaRepository<Rule, Long> {

    Rule findByRuleKey(String ruleKey);
}