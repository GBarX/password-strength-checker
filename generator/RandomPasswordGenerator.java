package generator;

import java.security.SecureRandom;

public class RandomPasswordGenerator implements PasswordGenerator {
    private int length;
    private boolean useUpper;
    private boolean useLower;
    private boolean useDigits;
    private boolean useSymbols;
    private SecureRandom random;

    private static final String UPPER_CASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWER_CASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String DIGITS = "1234567890";
    private static final String SYMBOLS = "!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~";

    public RandomPasswordGenerator(int length, boolean useUpper, boolean useLower, boolean useDigits,
            boolean useSymbols) {
        this.length = length;
        this.useUpper = useUpper;
        this.useLower = useLower;
        this.useDigits = useDigits;
        this.useSymbols = useSymbols;
        random = new SecureRandom();
    }

    private String buildCharPool() {
        StringBuilder sb = new StringBuilder();

        if (useUpper)
            sb.append(UPPER_CASE);
        if (useLower)
            sb.append(LOWER_CASE);
        if (useDigits)
            sb.append(DIGITS);
        if (useSymbols)
            sb.append(SYMBOLS);
        if (sb.length() == 0)
            throw new IllegalArgumentException("At least one character set must be enabled");

        return sb.toString();
    }

    public String generate() {
        String bcp = buildCharPool();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int r = random.nextInt(bcp.length());
            sb.append(bcp.charAt(r));
        }

        return sb.toString();
    }
}
