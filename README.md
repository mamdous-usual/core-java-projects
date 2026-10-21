# Core Java Projects

Eight beginner-friendly command-line projects for learning core Java, ordered from simple to more complex.
Every project is a single, heavily commented source file with its own folder and README, so you can
read it top to bottom as a lesson.

## Requirements

- **JDK 17 or newer** (the projects use records and switch expressions)
- Check with: `java -version` and `javac -version`

No build tool or external library is needed. Open a terminal in a project folder and run:

```bash
javac ClassName.java
java ClassName
```

## Projects

| #  | Project | Main concepts |
|----|---------|---------------|
| 01 | [Calculator](01-calculator) | methods, switch, loops, input validation, exceptions |
| 02 | [Number Guessing Game](02-number-guessing-game) | `Random`, nested loops, conditionals, constants |
| 03 | [Unit Converter](03-unit-converter) | enums, switch expressions, pure functions, `printf` |
| 04 | [Password Generator](04-password-generator) | `SecureRandom`, `StringBuilder`, collections, shuffling |
| 05 | [Word Counter](05-word-counter) | file I/O, try-with-resources, strings, command-line args |
| 06 | [To-Do List](06-todo-list) | classes and objects, encapsulation, `ArrayList` |
| 07 | [Simple Quiz](07-simple-quiz) | records, lists, char arithmetic, scoring |
| 08 | [Contact Book](08-contact-book) | `Map`/`TreeMap`, regex validation, sorting, layered design |

## How to study these

1. **Run it first.** Play with the program as a user before reading any code.
2. **Read `main` first**, then follow each method it calls. Use the comments as a guide.
3. **Break it on purpose.** Change a value, remove a check, and see which error appears and why.
4. **Do the "Try it yourself" extensions** in each README. Writing new code is where the learning sticks.
5. **Rewrite from memory.** After a few days, delete the file and rebuild the project from scratch.
