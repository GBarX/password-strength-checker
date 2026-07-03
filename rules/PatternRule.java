package rules;

import result.RuleResult;

public class PatternRule implements Rule {

    @Override
    public RuleResult evaluate(String password) {
        int strength = 0;
        String feedback = "";
        boolean hasPattern = false;
        String[] keyboardRows = {
                "qwertyuiop",
                "asdfghjkl",
                "zxcvbnm",
                "1234567890"
        };

        for (int i = 0; i < password.length() - 1; i++) {
            char current = password.charAt(i);
            char next = password.charAt(i + 1);

            if (next - current == 1)
                hasPattern = true;
            else if (current == next)
                hasPattern = true;
        }

        String lower = password.toLowerCase();
        for (int i = 0; i <= lower.length() - 4; i++) {
            String part = lower.substring(i, i + 4);
            for (String row : keyboardRows) {
                if (row.contains(part))
                    hasPattern = true;
            }
        }

        if (hasPattern) {
            strength = 1;
            feedback = "Predictable pattern detected.";
        } else {
            strength = 3;
            feedback = "No obvious patterns detected.";
        }

        return new RuleResult(strength, feedback);
    }
}
