package generator;

import java.io.IOException;

import evaluator.PasswordEvaluator;
import result.EvaluationResult;
import result.GenerationResult;

public class GeneratorService {
    private PasswordGenerator generator;
    private PasswordEvaluator evaluator;
    private double minEntropy;

    public GeneratorService(PasswordEvaluator evaluator, double minEntropy) {
        this.evaluator = evaluator;
        this.minEntropy = minEntropy;
    }

    public GenerationResult generateValidated() {
        String password;
        EvaluationResult evaResult;
        int attempts = 0;

        do {
            attempts++;
            if (attempts > 100)
                throw new IllegalStateException(
                        "Could not reach minEntropy after 100 attempts — threshold may be too high for current generator settings");
            password = generator.generate();
            evaResult = evaluator.evaluate(password);

        } while (evaResult.getEntropy() < minEntropy);

        return new GenerationResult(password, evaResult);
    }

    public void setGenerator(PasswordGenerator newPass) {
        this.generator = newPass;
    }
}
