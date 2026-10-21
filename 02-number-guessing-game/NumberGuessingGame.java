import java.util.Random;
import java.util.Scanner;

/**
 * Project 2: Number Guessing Game
 *
 * Concepts practiced: random numbers, loops, conditionals, counters,
 * constants, nested loops (play again), input validation.
 *
 * Run:
 *   javac NumberGuessingGame.java
 *   java NumberGuessingGame
 */
public class NumberGuessingGame {

    // Constants: "static final" values that never change.
    // Naming convention: UPPER_SNAKE_CASE.
    private static final int EASY_MAX = 50;
    private static final int MEDIUM_MAX = 100;
    private static final int HARD_MAX = 500;

    private static final Random RANDOM = new Random();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Number Guessing Game ===");

        // OUTER loop: lets the player play several rounds.
        boolean playAgain = true;
        while (playAgain) {
            int max = chooseDifficulty(scanner);
            playRound(scanner, max);

            System.out.print("\nPlay again? (y/n): ");
            playAgain = scanner.nextLine().trim().equalsIgnoreCase("y");
        }

        System.out.println("Thanks for playing!");
        scanner.close();
    }

    /** Asks the player for a difficulty and returns the upper bound. */
    private static int chooseDifficulty(Scanner scanner) {
        while (true) {
            System.out.println("\nChoose difficulty:");
            System.out.println("  1. Easy   (1-" + EASY_MAX + ")");
            System.out.println("  2. Medium (1-" + MEDIUM_MAX + ")");
            System.out.println("  3. Hard   (1-" + HARD_MAX + ")");
            System.out.print("Your choice: ");

            switch (scanner.nextLine().trim()) {
                case "1": return EASY_MAX;
                case "2": return MEDIUM_MAX;
                case "3": return HARD_MAX;
                default:  System.out.println("Please enter 1, 2 or 3.");
            }
        }
    }

    /** Plays a single round until the player guesses correctly. */
    private static void playRound(Scanner scanner, int max) {
        // nextInt(max) gives 0..max-1, so add 1 to get 1..max.
        int secret = RANDOM.nextInt(max) + 1;
        int attempts = 0;

        System.out.println("\nI'm thinking of a number between 1 and " + max + ".");

        // INNER loop: keep guessing until correct.
        while (true) {
            System.out.print("Your guess: ");
            String input = scanner.nextLine().trim();

            int guess;
            try {
                guess = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("  Please enter a whole number.");
                continue; // invalid input does NOT count as an attempt
            }

            if (guess < 1 || guess > max) {
                System.out.println("  Out of range! Pick between 1 and " + max + ".");
                continue;
            }

            attempts++;

            if (guess < secret) {
                System.out.println("  Too low!");
            } else if (guess > secret) {
                System.out.println("  Too high!");
            } else {
                System.out.println("  Correct! You got it in " + attempts + " attempt(s).");
                System.out.println("  " + rate(attempts, max));
                return; // ends this round
            }
        }
    }

    /**
     * A perfect player uses binary search, needing at most log2(max) guesses.
     * We use that to give a rating.
     */
    private static String rate(int attempts, int max) {
        int optimal = (int) Math.ceil(Math.log(max) / Math.log(2));
        if (attempts <= optimal) return "Excellent! That's optimal play.";
        if (attempts <= optimal * 2) return "Nice work!";
        return "Tip: guess the middle of the remaining range each time.";
    }
}
