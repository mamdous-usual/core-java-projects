import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Project 4: Password Generator
 *
 * Concepts practiced: StringBuilder, SecureRandom, collections, shuffling,
 * boolean flags, validating user criteria.
 *
 * Run:
 *   javac PasswordGenerator.java
 *   java PasswordGenerator
 */
public class PasswordGenerator {

    private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL = "!@#$%^&*()-_=+[]{};:,.<>?";

    // SecureRandom is cryptographically strong. For passwords, ALWAYS use it
    // instead of java.util.Random, whose output can be predicted.
    private static final SecureRandom RANDOM = new SecureRandom();

    /**
     * Generates a password.
     *
     * Strategy:
     *  1. Guarantee at least one character from each selected category.
     *  2. Fill the remaining slots from the combined pool.
     *  3. Shuffle so the guaranteed characters are not always at the start.
     */
    static String generate(int length, boolean useUpper, boolean useDigits, boolean useSpecial) {
        // Build the pool of allowed characters + list of required categories.
        StringBuilder pool = new StringBuilder(LOWERCASE);
        List<String> requiredCategories = new ArrayList<>();
        requiredCategories.add(LOWERCASE);

        if (useUpper) {
            pool.append(UPPERCASE);
            requiredCategories.add(UPPERCASE);
        }
        if (useDigits) {
            pool.append(DIGITS);
            requiredCategories.add(DIGITS);
        }
        if (useSpecial) {
            pool.append(SPECIAL);
            requiredCategories.add(SPECIAL);
        }

        if (length < requiredCategories.size()) {
            throw new IllegalArgumentException(
                    "Length must be at least " + requiredCategories.size() + " for the selected options.");
        }

        List<Character> chars = new ArrayList<>();

        // Step 1: one guaranteed character per category.
        for (String category : requiredCategories) {
            chars.add(randomChar(category));
        }

        // Step 2: fill the rest from the full pool.
        while (chars.size() < length) {
            chars.add(randomChar(pool.toString()));
        }

        // Step 3: shuffle with our SecureRandom.
        Collections.shuffle(chars, RANDOM);

        // Convert List<Character> -> String.
        StringBuilder password = new StringBuilder();
        for (char c : chars) {
            password.append(c);
        }
        return password.toString();
    }

    private static char randomChar(String source) {
        return source.charAt(RANDOM.nextInt(source.length()));
    }

    /** Rough strength estimate based on length and variety. */
    static String strength(int length, boolean useUpper, boolean useDigits, boolean useSpecial) {
        int poolSize = LOWERCASE.length();
        if (useUpper) poolSize += UPPERCASE.length();
        if (useDigits) poolSize += DIGITS.length();
        if (useSpecial) poolSize += SPECIAL.length();

        // Entropy in bits = length * log2(poolSize)
        double bits = length * (Math.log(poolSize) / Math.log(2));
        String label;
        if (bits < 40) label = "Weak";
        else if (bits < 60) label = "Fair";
        else if (bits < 80) label = "Strong";
        else label = "Very strong";
        return String.format("%s (~%.0f bits of entropy)", label, bits);
    }

    private static boolean askYesNo(Scanner scanner, String question) {
        System.out.print(question + " (y/n): ");
        return scanner.nextLine().trim().equalsIgnoreCase("y");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Password Generator ===");

        while (true) {
            int length;
            System.out.print("\nPassword length (8-64, or 0 to quit): ");
            try {
                length = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number.");
                continue;
            }

            if (length == 0) {
                System.out.println("Goodbye!");
                break;
            }
            if (length < 8 || length > 64) {
                System.out.println("Length must be between 8 and 64.");
                continue;
            }

            boolean upper = askYesNo(scanner, "Include uppercase letters?");
            boolean digits = askYesNo(scanner, "Include digits?");
            boolean special = askYesNo(scanner, "Include special characters?");

            String password = generate(length, upper, digits, special);
            System.out.println("\nPassword : " + password);
            System.out.println("Strength : " + strength(length, upper, digits, special));
        }

        scanner.close();
    }
}
