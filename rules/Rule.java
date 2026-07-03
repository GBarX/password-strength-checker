package rules;

import result.RuleResult;

public interface Rule {
    RuleResult evaluate(String password);
}
