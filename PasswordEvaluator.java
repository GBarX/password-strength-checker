import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class PasswordEvaluator {
    private List<Rule> rules;

    public PasswordEvaluator() throws IOException {
        rules = new ArrayList<>();
        rules.add(new LengthRule());
        rules.add(new CharVarietyRule());
        rules.add(new PatternRule());
        rules.add(new BlackListRule("resources/10k-most-common.txt"));
    }

    private String determineLevel(int totalStrength) {
        if (totalStrength <= 4)
            return "Weak";
        else if (totalStrength <= 8)
            return "Medium";
        else
            return "Strong";
    }

    private double calculateEntropy(String password) {
        boolean hasLower = false, hasUpper = false, hasDigit = false, hasSpecial = false;
        for (char c : password.toCharArray()) {
            if (Character.isLowerCase(c))
                hasLower = true;
            if (Character.isUpperCase(c))
                hasUpper = true;
            if (Character.isDigit(c))
                hasDigit = true;
            if (!Character.isLetterOrDigit(c))
                hasSpecial = true;
        }
        int pool = 0;
        if (hasLower)
            pool += 26;
        if (hasUpper)
            pool += 26;
        if (hasDigit)
            pool += 10;
        if (hasSpecial)
            pool += 32;

        double entropy = password.length() * (Math.log(pool) / Math.log(2));
        return entropy;
    }

    private String calculateCrackTime(double entropy) {
        double guess = Math.pow(2, entropy);
        double seconds = guess / 10_000_000_000.0;
        if (seconds < 60)
            return "less than a minute";
        else if (seconds < 3600)
            return (seconds / 60) + " minutes";
        else if (seconds < 86400)
            return (seconds / 3600) + " hours";
        else if (seconds < 31536000)
            return (seconds / 86400) + " days";
        else
            return (seconds / 31536000) + " years";
    }

    public EvaluationResult evaluate(String password) {
        List<String> feedbacks = new ArrayList<>();
        int totalStrength = 0;

        for (Rule rule : rules) {
            RuleResult result = rule.evaluate(password);

            feedbacks.add(result.getFeedback());
            totalStrength += result.getStrength();
        }

        String level = determineLevel(totalStrength);
        double entropy = calculateEntropy(password);
        String cct = calculateCrackTime(entropy);

        return new EvaluationResult(level, entropy, cct, feedbacks);
    }
}
