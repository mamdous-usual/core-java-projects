# 06 - To-Do List

Add, view, complete and remove tasks from the command line.

## Run

```bash
javac TodoList.java
java TodoList
```

## What you practice

- Your first real **class** (`Task`) with private fields, a constructor, getters and `toString()`
- **Encapsulation**: the `completed` flag can only change through `markCompleted()`
- **`ArrayList`** operations: `add`, `get`, `remove`, `size`, `isEmpty`
- Converting between **1-based user numbers** and **0-based list indexes**
- Bounds checking before touching a list (avoids `IndexOutOfBoundsException`)
- A first look at **streams** (`filter(...).count()`)

## Study guide

1. Notice how `TodoList` (the manager) and `Task` (the data) have separate responsibilities.
   This "one class, one job" idea is the foundation of object-oriented design.
2. Read `isValidNumber` and see where it is used. Remove it and trigger the exception on purpose to see what happens.
3. Why is `description` `final` but `completed` not?

## Try it yourself (extensions)

- Add a due date and sort tasks by it.
- Save tasks to a file on exit and load them on start (reuse what you learned in project 05).
- Add priorities (high / medium / low) using an enum.
- Add an "undo last delete" feature with a second list or stack.
