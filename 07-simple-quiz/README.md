# 07 - Simple Quiz

A multiple-choice Java quiz with shuffled questions, instant feedback and a final score.

## Run

```bash
javac SimpleQuiz.java
java SimpleQuiz
```

## What you practice

- **Records** for immutable data (`Question`) including a validating *compact constructor*
- Working with **`List`**, `List.of(...)` and `Collections.shuffle`
- **Char arithmetic**: `'A' + 1` is `'B'`, and `input.charAt(0) - 'A'` converts a letter to an index
- Input validation loops
- Score calculation and `printf` percentages
- Keeping **data** (question bank) separate from **logic** (`ask`) and **UI** (`main`)

## Study guide

1. The `Question` record validates `correctIndex` the moment it is created, so a broken question
   fails immediately (fail fast) instead of causing a confusing bug later.
2. `List.of(...)` returns an *immutable* list, so `buildQuestions` wraps it in `new ArrayList<>(...)`
   so that it can be shuffled. Try removing the wrapper and read the exception you get.
3. Follow how `ask()` returns a `boolean`, so the caller can count points without knowing how asking works.

## Try it yourself (extensions)

- Load questions from a text or CSV file instead of hard-coding them.
- Add categories and let the player pick one.
- Add a timer per question using `System.currentTimeMillis()`.
- Shuffle the *options* of each question too (remember to track the correct one!).
