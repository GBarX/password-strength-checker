package result;

public class GenerationResult {
    private String password;
    private EvaluationResult evaluation;

    public GenerationResult(String password, EvaluationResult evaluation) {
        this.password = password;
        this.evaluation = evaluation;
    }

    public String getPassword() {
        return password;
    }

    public EvaluationResult getEvaluation() {
        return evaluation;
    }
}
