package generator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

public class PassphraseGenerator implements PasswordGenerator {
    private int wordCount;
    private String separator;
    private List<String> wordList;
    private SecureRandom random;

    public PassphraseGenerator(int wordCount, String seperator) throws IOException {
        this.wordCount = wordCount;
        this.separator = seperator;
        random = new SecureRandom();
        loadWordList();
    }

    public String generate() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < wordCount; i++) {
            if (i > 0)
                sb.append(separator);
            String word = wordList.get(random.nextInt(wordList.size()));
            sb.append(word);
        }

        return sb.toString();
    }

    private void loadWordList() throws IOException {
        wordList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("resources/eff_large_wordlist.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] splitted = line.split("\t");
                wordList.add(splitted[1]);
            }
        }
    }
}
