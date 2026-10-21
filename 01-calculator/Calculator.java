import java.util.Scanner;

/**
 * Project 1: Calculator
 *
 * Concepts practiced: methods, switch statements, loops, Scanner input,
 * basic input validation, exception handling.
 *
 * Run:
 *   javac Calculator.java
 *   java Calculator
 */
public class Calculator {

    // ---------------------------------------------------------------
    // Each operation is its own small method. This is "separation of
    // concerns": the math knows nothing about the keyboard or screen.
    // ---------------------------------------------------------------

    static double add(double a, double b) {
        return a + b;
    }

    static double subtract(double a, double b) {
        return a - b;
    }

    static double multiply(double a, double b) {
        return a * b;
    }

    static double divide(double a, double b) {
        // Dividing doubles by zero gives Infinity in Java, which is
        // confusing for users. We throw an exception instead.
        if (b == 0) {
            throw new ArithmeticException("Cannot divide by zero.");
        }
        return a / b;
    }

    // ---------------------------------------------------------------
    // Helper: keep asking until the user types a valid number.
    // Demonstrates a loop + try/catch for bad input.
    // ---------------------------------------------------------------
    static double readNumber(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim();
            try {
                return Double.parseDouble(line);
            } catch (NumberFormatException e) {
                System.out.println("  '" + line + "' is not a valid number. Try again.");
            }
        }
    }

    static void printMenu() {
        System.out.println();
        System.out.println("=== Calculator ===");
        System.out.println("1. Add");
        System.out.println("2. Subtract");
        System.out.println("3. Multiply");
        System.out.println("4. Divide");
        System.out.println("0. Exit");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Main program loop: show menu -> do work -> repeat until exit.
        while (true) {
            printMenu();
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine().trim();

            if (choice.equals("0")) {
                System.out.println("Goodbye!");
                break; // leaves the while loop
            }

            // Validate the menu choice BEFORE asking for numbers.
            if (!choice.matches("[1-4]")) {
                System.out.println("Invalid option. Please choose 0-4.");
                continue; // jump back to the top of the loop
            }

            double a = readNumber(scanner, "Enter first number: ");
            double b = readNumber(scanner, "Enter second number: ");

            try {
                // Modern switch expression (Java 14+): returns a value.
                double result = switch (choice) {
                    case "1" -> add(a, b);
                    case "2" -> subtract(a, b);
                    case "3" -> multiply(a, b);
                    case "4" -> divide(a, b);
                    default -> throw new IllegalStateException("Unreachable");
                };
                System.out.println("Result: " + result);
            } catch (ArithmeticException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        scanner.close();
    }
}
