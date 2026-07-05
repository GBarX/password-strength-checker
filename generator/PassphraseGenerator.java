package generator;

import java.security.SecureRandom;
import java.util.List;

public class PassphraseGenerator implements PasswordGenerator {
    private int wordCount;
    private String separator;
    private List<String> wordList;
    private SecureRandom random;

    public String generate() {
        return "";
    }

    private void loadWordList() {

    }
}
