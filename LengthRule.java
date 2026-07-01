public class LengthRule implements Rule {

    @Override
    public RuleResult evaluate(String password) {
        int length = password.length();

        int strength;
        String feedback;

        if (length <= 0) {
            strength = 0;
            feedback = "You didn't write a password.";
        } else if (length <= 8) {
            strength = 0;
            feedback = "Your password is so short! Please use a longer password.";
        } else if (length > 8 && length <= 12) {
            strength = 1;
            feedback = "Your password length is medium. You might use a longer password.";
        } else {
            strength = 2;
            feedback = "Your password length is great!";
        }

        return new RuleResult(strength, feedback);
    }
}
