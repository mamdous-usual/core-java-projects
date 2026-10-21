import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * Project 5: Word Counter
 *
 * Counts lines, words and characters in a text file (like the Unix `wc` command).
 *
 * Concepts practiced: file I/O, try-with-resources, exceptions (IOException),
 * string manipulation (split, trim, isEmpty), command-line arguments.
 *
 * Run:
 *   javac WordCounter.java
 *   java WordCounter sample.txt        (pass a file path as an argument)
 *   java WordCounter                   (or be asked for the path)
 */
public class WordCounter {

    /** A small holder for the three results. Records (Java 16+) are perfect for this. */
    record Stats(int lines, int words, int characters) { }

    /**
     * Reads the file line by line and accumulates the counts.
     *
     * try-with-resources: the BufferedReader is closed AUTOMATICALLY when the
     * block ends, even if an exception happens. Never forget to close files.
     */
    static Stats count(String path) throws IOException {
        int lines = 0;
        int words = 0;
        int characters = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            // readLine() returns null at end of file.
            while ((line = reader.readLine()) != null) {
                lines++;
                // +1 accounts for the newline character that readLine() strips.
                characters += line.length() + 1;

                String trimmed = line.trim();
                if (!trimmed.isEmpty()) {
                    // "\\s+" is a regex meaning "one or more whitespace characters".
                    words += trimmed.split("\\s+").length;
                }
            }
        }
        return new Stats(lines, words, characters);
    }

    public static void main(String[] args) {
        String path;

        if (args.length > 0) {
            path = args[0]; // command-line argument
        } else {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter path to a text file: ");
            path = scanner.nextLine().trim();
        }

        try {
            Stats stats = count(path);
            System.out.println("File       : " + path);
            System.out.println("Lines      : " + stats.lines());
            System.out.println("Words      : " + stats.words());
            System.out.println("Characters : " + stats.characters());
        } catch (IOException e) {
            // FileNotFoundException is a subclass of IOException.
            System.out.println("Could not read file: " + e.getMessage());
        }
    }
}
