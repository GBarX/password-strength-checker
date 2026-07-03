package result;

public class RuleResult {
    private int strength;
    private String feedback;

    public RuleResult(int strength, String feedback) {
        this.strength = strength;
        this.feedback = feedback;
    }

    public int getStrength() {
        return strength;
    }

    public String getFeedback() {
        return feedback;
    }

}
