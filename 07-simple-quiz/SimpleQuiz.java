import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Project 7: Simple Quiz
 *
 * Concepts practiced: records, lists of objects, arrays, shuffling,
 * conditionals, score tracking, input validation.
 *
 * Run:
 *   javac SimpleQuiz.java
 *   java SimpleQuiz
 */
public class SimpleQuiz {

    /**
     * A record is a compact immutable data class (Java 16+).
     * Java automatically generates the constructor, getters (question(), options()...),
     * equals(), hashCode() and toString().
     *
     * correctIndex is 0-based: 0 means the first option is correct.
     */
    record Question(String text, List<String> options, int correctIndex) {

        // Compact constructor: runs validation when a record is created.
        Question {
            if (correctIndex < 0 || correctIndex >= options.size()) {
                throw new IllegalArgumentException("correctIndex out of range for: " + text);
            }
        }

        boolean isCorrect(int chosenIndex) {
            return chosenIndex == correctIndex;
        }
    }

    /** The question bank. Adding a question is just adding one line. */
    private static List<Question> buildQuestions() {
        return new java.util.ArrayList<>(List.of(
                new Question("Which keyword is used to create a subclass in Java?",
                        List.of("implements", "extends", "inherits", "super"), 1),
                new Question("What is the size of an int in Java?",
                        List.of("16 bits", "32 bits", "64 bits", "It depends on the OS"), 1),
                new Question("Which collection does NOT allow duplicate elements?",
                        List.of("ArrayList", "LinkedList", "HashSet", "Vector"), 2),
                new Question("What does JVM stand for?",
                        List.of("Java Variable Method", "Java Virtual Machine", "Joint Visual Model", "Java Verified Module"), 1),
                new Question("Which method is the entry point of a Java program?",
                        List.of("start()", "run()", "main()", "init()"), 2),
                new Question("Which of these is NOT a primitive type?",
                        List.of("int", "boolean", "String", "double"), 2),
                new Question("What is the default value of an uninitialised int field?",
                        List.of("null", "0", "1", "undefined"), 1)
        ));
    }

    /** Asks one question, returns true if the answer was correct. */
    private static boolean ask(Scanner scanner, Question q, int number, int total) {
        System.out.println("\nQuestion " + number + "/" + total + ": " + q.text());

        // Letters A, B, C, D computed from the index: 'A' + 0 = 'A', 'A' + 1 = 'B', ...
        for (int i = 0; i < q.options().size(); i++) {
            System.out.println("  " + (char) ('A' + i) + ") " + q.options().get(i));
        }

        // Keep asking until the input is a valid letter.
        int chosen;
        while (true) {
            System.out.print("Your answer: ");
            String input = scanner.nextLine().trim().toUpperCase();
            if (input.length() == 1) {
                chosen = input.charAt(0) - 'A';
                if (chosen >= 0 && chosen < q.options().size()) {
                    break;
                }
            }
            System.out.println("  Please enter a letter between A and "
                    + (char) ('A' + q.options().size() - 1) + ".");
        }

        if (q.isCorrect(chosen)) {
            System.out.println("  Correct!");
            return true;
        } else {
            char correctLetter = (char) ('A' + q.correctIndex());
            System.out.println("  Wrong. The correct answer was " + correctLetter
                    + ") " + q.options().get(q.correctIndex()));
            return false;
        }
    }

    private static String feedback(int score, int total) {
        double percent = 100.0 * score / total;
        if (percent == 100) return "Perfect score!";
        if (percent >= 70) return "Great job!";
        if (percent >= 40) return "Not bad, keep practising.";
        return "Review the basics and try again.";
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Java Quiz ===");

        boolean again = true;
        while (again) {
            List<Question> questions = buildQuestions();
            Collections.shuffle(questions); // different order each time

            int score = 0;
            for (int i = 0; i < questions.size(); i++) {
                if (ask(scanner, questions.get(i), i + 1, questions.size())) {
                    score++;
                }
            }

            System.out.println("\n=== Result ===");
            System.out.printf("You scored %d out of %d (%.0f%%)%n",
                    score, questions.size(), 100.0 * score / questions.size());
            System.out.println(feedback(score, questions.size()));

            System.out.print("\nTake the quiz again? (y/n): ");
            again = scanner.nextLine().trim().equalsIgnoreCase("y");
        }

        System.out.println("Thanks for playing!");
        scanner.close();
    }
}
