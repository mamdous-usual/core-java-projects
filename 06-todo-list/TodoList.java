import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Project 6: To-Do List
 *
 * Concepts practiced: classes and objects, encapsulation, ArrayList,
 * toString(), index-based access, menu loops.
 *
 * Run:
 *   javac TodoList.java
 *   java TodoList
 */
public class TodoList {

    /**
     * A Task is a small class that bundles data (description, done) together.
     * Fields are private (encapsulation): outside code must use the methods.
     * "static" nested class: it does not need an instance of TodoList.
     */
    static class Task {
        private final String description;
        private boolean completed;

        Task(String description) {
            this.description = description;
            this.completed = false; // new tasks start incomplete
        }

        String getDescription() {
            return description;
        }

        boolean isCompleted() {
            return completed;
        }

        void markCompleted() {
            this.completed = true;
        }

        // toString() controls how the object prints.
        @Override
        public String toString() {
            return (completed ? "[x] " : "[ ] ") + description;
        }
    }

    // The data store: a resizable list of Task objects.
    private final List<Task> tasks = new ArrayList<>();

    void addTask(String description) {
        tasks.add(new Task(description));
        System.out.println("Added: " + description);
    }

    /** @param number 1-based number as shown to the user. */
    void removeTask(int number) {
        if (!isValidNumber(number)) {
            System.out.println("No task with number " + number + ".");
            return;
        }
        // Users count from 1, but lists count from 0, so subtract 1.
        Task removed = tasks.remove(number - 1);
        System.out.println("Removed: " + removed.getDescription());
    }

    void completeTask(int number) {
        if (!isValidNumber(number)) {
            System.out.println("No task with number " + number + ".");
            return;
        }
        Task task = tasks.get(number - 1);
        if (task.isCompleted()) {
            System.out.println("Already completed.");
        } else {
            task.markCompleted();
            System.out.println("Completed: " + task.getDescription());
        }
    }

    void showTasks() {
        if (tasks.isEmpty()) {
            System.out.println("Your list is empty. Add something!");
            return;
        }
        System.out.println("\n--- Your Tasks ---");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i)); // uses toString()
        }
        long done = tasks.stream().filter(Task::isCompleted).count();
        System.out.println("(" + done + "/" + tasks.size() + " completed)");
    }

    private boolean isValidNumber(int number) {
        return number >= 1 && number <= tasks.size();
    }

    // ---------------------------------------------------------------
    // User interface
    // ---------------------------------------------------------------

    private static int readInt(Scanner scanner, String prompt) {
        System.out.print(prompt);
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1; // -1 is never a valid task number, so callers report an error
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TodoList todo = new TodoList();

        while (true) {
            System.out.println("\n=== To-Do List ===");
            System.out.println("1. Add task");
            System.out.println("2. View tasks");
            System.out.println("3. Mark task as complete");
            System.out.println("4. Remove task");
            System.out.println("0. Exit");
            System.out.print("Choose: ");

            switch (scanner.nextLine().trim()) {
                case "1" -> {
                    System.out.print("Task description: ");
                    String description = scanner.nextLine().trim();
                    if (description.isEmpty()) {
                        System.out.println("Description cannot be empty.");
                    } else {
                        todo.addTask(description);
                    }
                }
                case "2" -> todo.showTasks();
                case "3" -> {
                    todo.showTasks();
                    todo.completeTask(readInt(scanner, "Task number to complete: "));
                }
                case "4" -> {
                    todo.showTasks();
                    todo.removeTask(readInt(scanner, "Task number to remove: "));
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
