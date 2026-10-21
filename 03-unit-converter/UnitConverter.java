import java.util.Scanner;

/**
 * Project 3: Unit Converter
 *
 * Concepts practiced: methods with parameters and return values, enums,
 * switch expressions, formatted output, reading user input.
 *
 * Run:
 *   javac UnitConverter.java
 *   java UnitConverter
 */
public class UnitConverter {

    // An enum is a fixed set of named values. It is safer than using
    // plain strings or ints because the compiler checks them for you.
    enum Conversion {
        KM_TO_MILES("Kilometers", "Miles"),
        MILES_TO_KM("Miles", "Kilometers"),
        CELSIUS_TO_FAHRENHEIT("Celsius", "Fahrenheit"),
        FAHRENHEIT_TO_CELSIUS("Fahrenheit", "Celsius"),
        KG_TO_POUNDS("Kilograms", "Pounds"),
        POUNDS_TO_KG("Pounds", "Kilograms"),
        METERS_TO_FEET("Meters", "Feet"),
        FEET_TO_METERS("Feet", "Meters");

        final String fromUnit;
        final String toUnit;

        Conversion(String fromUnit, String toUnit) {
            this.fromUnit = fromUnit;
            this.toUnit = toUnit;
        }
    }

    // Conversion factors as named constants (no "magic numbers" in code).
    private static final double KM_PER_MILE = 1.609344;
    private static final double KG_PER_POUND = 0.45359237;
    private static final double METERS_PER_FOOT = 0.3048;

    /** The core logic: pure function, takes a value, returns the converted value. */
    static double convert(Conversion type, double value) {
        return switch (type) {
            case KM_TO_MILES -> value / KM_PER_MILE;
            case MILES_TO_KM -> value * KM_PER_MILE;
            case CELSIUS_TO_FAHRENHEIT -> value * 9 / 5 + 32;
            case FAHRENHEIT_TO_CELSIUS -> (value - 32) * 5 / 9;
            case KG_TO_POUNDS -> value / KG_PER_POUND;
            case POUNDS_TO_KG -> value * KG_PER_POUND;
            case METERS_TO_FEET -> value / METERS_PER_FOOT;
            case FEET_TO_METERS -> value * METERS_PER_FOOT;
        };
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Conversion[] options = Conversion.values(); // array of all enum constants

        while (true) {
            System.out.println("\n=== Unit Converter ===");
            for (int i = 0; i < options.length; i++) {
                System.out.printf("%d. %s -> %s%n", i + 1, options[i].fromUnit, options[i].toUnit);
            }
            System.out.println("0. Exit");
            System.out.print("Choose a conversion: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number from the menu.");
                continue;
            }

            if (choice == 0) {
                System.out.println("Goodbye!");
                break;
            }
            if (choice < 1 || choice > options.length) {
                System.out.println("Invalid choice.");
                continue;
            }

            Conversion selected = options[choice - 1];

            System.out.print("Enter value in " + selected.fromUnit + ": ");
            double value;
            try {
                value = Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("That's not a valid number.");
                continue;
            }

            double result = convert(selected, value);
            // %.2f means "floating point with 2 decimal places".
            System.out.printf("%.2f %s = %.2f %s%n", value, selected.fromUnit, result, selected.toUnit);
        }

        scanner.close();
    }
}
