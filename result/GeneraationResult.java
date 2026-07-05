package result;

public class GeneraationResult {
    private String password;
    private EvaluationResult evaluation;

    public String getPassword() {
        return "";
    }

    public EvaluationResult getEvaluation() {
        return new EvaluationResult(password, 0, password, null);
    }
}
