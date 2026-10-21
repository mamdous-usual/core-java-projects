import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * Project 8: Contact Book
 *
 * Concepts practiced: Map (key -> value) data structures, records,
 * sorting, searching, simple validation with regex, separating
 * storage logic from user interface.
 *
 * Run:
 *   javac ContactBook.java
 *   java ContactBook
 */
public class ContactBook {

    /** Immutable contact data. */
    record Contact(String name, String phone, String email) {
        @Override
        public String toString() {
            return String.format("%-20s %-18s %s", name, phone, email.isEmpty() ? "-" : email);
        }
    }

    // ---------------------------------------------------------------
    // Storage layer. A TreeMap keeps keys SORTED automatically.
    // The key is the lower-cased name so "Alice" and "alice" are the same
    // contact (case-insensitive lookups).
    // ---------------------------------------------------------------
    private final Map<String, Contact> contacts = new TreeMap<>();

    private static String keyOf(String name) {
        return name.trim().toLowerCase();
    }

    /** @return true if added, false if a contact with that name already exists. */
    boolean add(Contact contact) {
        String key = keyOf(contact.name());
        if (contacts.containsKey(key)) {
            return false;
        }
        contacts.put(key, contact);
        return true;
    }

    /** @return true if a contact was removed. */
    boolean delete(String name) {
        return contacts.remove(keyOf(name)) != null;
    }

    Contact find(String name) {
        return contacts.get(keyOf(name)); // null if missing
    }

    /** Partial, case-insensitive search on name or phone. */
    List<Contact> search(String text) {
        String needle = text.trim().toLowerCase();
        List<Contact> matches = new ArrayList<>();
        for (Contact c : contacts.values()) {
            if (c.name().toLowerCase().contains(needle) || c.phone().contains(needle)) {
                matches.add(c);
            }
        }
        return matches;
    }

    List<Contact> all() {
        List<Contact> list = new ArrayList<>(contacts.values());
        list.sort(Comparator.comparing(c -> c.name().toLowerCase()));
        return list;
    }

    // ---------------------------------------------------------------
    // Validation helpers
    // ---------------------------------------------------------------

    /** Accepts digits, spaces, dashes, parentheses and an optional leading +. 7-15 digits. */
    static boolean isValidPhone(String phone) {
        if (!phone.matches("\\+?[0-9\\s\\-()]+")) {
            return false;
        }
        int digits = phone.replaceAll("[^0-9]", "").length();
        return digits >= 7 && digits <= 15;
    }

    /** Very simple email check: something@something.something (or empty = optional). */
    static boolean isValidEmail(String email) {
        return email.isEmpty() || email.matches("[^@\\s]+@[^@\\s]+\\.[^@\\s]+");
    }

    // ---------------------------------------------------------------
    // User interface
    // ---------------------------------------------------------------

    private static void printContacts(List<Contact> list) {
        if (list.isEmpty()) {
            System.out.println("No contacts found.");
            return;
        }
        System.out.println(String.format("%-20s %-18s %s", "NAME", "PHONE", "EMAIL"));
        System.out.println("-".repeat(58));
        for (Contact c : list) {
            System.out.println(c);
        }
        System.out.println("(" + list.size() + " contact(s))");
    }

    private static void addContactFlow(Scanner scanner, ContactBook book) {
        System.out.print("Name: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) {
            System.out.println("Name cannot be empty.");
            return;
        }

        System.out.print("Phone: ");
        String phone = scanner.nextLine().trim();
        if (!isValidPhone(phone)) {
            System.out.println("Invalid phone number.");
            return;
        }

        System.out.print("Email (optional): ");
        String email = scanner.nextLine().trim();
        if (!isValidEmail(email)) {
            System.out.println("Invalid email address.");
            return;
        }

        if (book.add(new Contact(name, phone, email))) {
            System.out.println("Contact saved.");
        } else {
            System.out.println("A contact named '" + name + "' already exists.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ContactBook book = new ContactBook();

        while (true) {
            System.out.println("\n=== Contact Book ===");
            System.out.println("1. Add contact");
            System.out.println("2. View all contacts");
            System.out.println("3. Search contacts");
            System.out.println("4. Delete contact");
            System.out.println("0. Exit");
            System.out.print("Choose: ");

            switch (scanner.nextLine().trim()) {
                case "1" -> addContactFlow(scanner, book);
                case "2" -> printContacts(book.all());
                case "3" -> {
                    System.out.print("Search for (name or phone): ");
                    printContacts(book.search(scanner.nextLine()));
                }
                case "4" -> {
                    System.out.print("Name of contact to delete: ");
                    String name = scanner.nextLine();
                    if (book.delete(name)) {
                        System.out.println("Deleted.");
                    } else {
                        System.out.println("No contact named '" + name.trim() + "'.");
                    }
                }
                case "0" -> {
                    System.out.println("Goodbye!");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }
}
