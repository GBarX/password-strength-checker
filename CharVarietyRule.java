public class CharVarietyRule implements Rule {

    @Override
    public RuleResult evaluate(String password) {
        int strength = 0;
        String feedback = "";

        boolean upperCase = false;
        boolean lowerCase = false;
        boolean digit = false;
        boolean hasSpecial = false;

        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c))
                upperCase = true;
            if (Character.isLowerCase(c))
                lowerCase = true;
            if (Character.isDigit(c))
                digit = true;
            if (!Character.isLetterOrDigit(c))
                hasSpecial = true;
        }

        if (upperCase)
            strength++;
        if (lowerCase)
            strength++;
        if (digit)
            strength++;
        if (hasSpecial)
            strength++;

        if (strength <= 1)
            feedback = "Use a mix of character types.";
        else if (strength <= 2)
            feedback = "Decent variety, but you can add more. ";
        else if (strength == 4)
            feedback = "great character variety!";

        return new RuleResult(strength, feedback);
    }
}
