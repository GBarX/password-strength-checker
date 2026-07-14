import java.io.IOException;

import evaluator.PasswordEvaluator;
import generator.GeneratorService;
import generator.PassphraseGenerator;
import generator.PasswordGenerator;
import generator.RandomPasswordGenerator;
import result.EvaluationResult;
import result.GenerationResult;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        PasswordEvaluator evaluator = new PasswordEvaluator();

        System.out.println("Choose an option:");
        System.out.println("1 - Test your password strength!");
        System.out.println("2 - Generate strong password");
        System.out.println("3 - Generate Passphrase");
        int choice = sc.nextInt();
        sc.nextLine();

        GeneratorService service = new GeneratorService(evaluator, 60);

        switch (choice) {
            case 1:
                System.out.println("Enter your password to see your password's strength: ");
                String password = sc.nextLine();
                EvaluationResult result = evaluator.evaluate(password);

                System.out.println("Level: " + result.getLevel());
                System.out.println("Entropy: " + result.getEntropy() + " bits");
                System.out.println("Crack time: " + result.getCrackTime());
                System.out.println("Feedback:");
                for (String msg : result.getMessages()) {
                    System.out.println(" - " + msg);
                }
                break;
            case 2:
                System.out.println("Enter your password length: ");
                int length = sc.nextInt();
                System.out.println("Upper case? (True/False): ");
                boolean upperCase = sc.nextBoolean();
                System.out.println("Lower case? (True/False): ");
                boolean lowerCase = sc.nextBoolean();
                System.out.println("Digits? (True/False): ");
                boolean digits = sc.nextBoolean();
                System.out.println("Symbols? (True/False): ");
                boolean symbols = sc.nextBoolean();

                RandomPasswordGenerator passGen = new RandomPasswordGenerator(length, upperCase, lowerCase, digits,
                        symbols);
                service.setGenerator(passGen);

                GenerationResult gr = service.generateValidated();
                System.out.println("Password: " + gr.getPassword());
                System.out.println("Level: " + gr.getEvaluation().getLevel());
                System.out.println("Entropy: " + gr.getEvaluation().getEntropy());
                break;
            case 3:
                System.out.println("How many words? : ");
                int wordCount = sc.nextInt();
                sc.nextLine();
                System.out.println("Enter separator type: ");
                String separator = sc.nextLine();

                PassphraseGenerator passPhr = new PassphraseGenerator(wordCount, separator);
                service.setGenerator(passPhr);
                GenerationResult grP = service.generateValidated();
                System.out.println("Password: " + grP.getPassword());
                System.out.println("Password level: " + grP.getEvaluation().getLevel());
                System.out.println("Entropy: " + grP.getEvaluation().getEntropy());
                break;
            default:
                System.out.println("Invalid input!");
                break;
        }

        sc.close();
    }
}
