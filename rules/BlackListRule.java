package rules;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

import result.RuleResult;

public class BlackListRule implements Rule {
    private Set<String> commonPasswords = new HashSet<>();

    public BlackListRule(String filePath) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                commonPasswords.add(line.trim().toLowerCase());
            }
        }
    }

    @Override
    public RuleResult evaluate(String password) {
        int strength = 0;
        String feedback = "";

        if (commonPasswords.contains(password)) {
            strength = 0;
            feedback = "Your password is weak password! Try another one.";
        } else {
            strength = 1;
            feedback = "your password is not included in the blacklisted passwords! It is great.";
        }

        return new RuleResult(strength, feedback);
    }
}
