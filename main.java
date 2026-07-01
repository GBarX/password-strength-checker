import java.io.IOException;

public class main {
    public static void main(String[] args) throws IOException {
        PasswordEvaluator evaluator = new PasswordEvaluator();

        String password = "test1234!";
        String password2 = "testlkwq1";
        String password3 = "qwer125678";
        String password4 = "1kasd1!DJamsiqkals!?AQsnzjQ@kau";
        String password5 = "12KAShax";
        String password6 = "QAZ123ZAQ";

        EvaluationResult result = evaluator.evaluate(password3);

        System.out.println("Level: " + result.getLevel());
        System.out.println("Entropy: " + result.getEntropy() + " bits");
        System.out.println("Crack time: " + result.getCrackTime());
        System.out.println("Feedback:");
        for (String msg : result.getMessages()) {
            System.out.println(" - " + msg);
        }
    }
}
