package result;

import java.util.List;

public class EvaluationResult {
    private String level;
    private double entropy;
    private String crackTime;
    private List<String> messages;

    public EvaluationResult(String level, double entropy, String crackTime, List<String> messages) {
        this.level = level;
        this.entropy = entropy;
        this.crackTime = crackTime;
        this.messages = messages;
    }

    // Getter && Setter
    public String getLevel() {
        return level;
    }

    public double getEntropy() {
        return entropy;
    }

    public String getCrackTime() {
        return crackTime;
    }

    public List<String> getMessages() {
        return messages;
    }
}
