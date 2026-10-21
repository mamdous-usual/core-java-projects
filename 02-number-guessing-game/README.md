# 02 - Number Guessing Game

The program picks a secret number; you guess until you find it, with "too high / too low" hints.

## Run

```bash
javac NumberGuessingGame.java
java NumberGuessingGame
```

## What you practice

- **Random numbers** with `java.util.Random`
- **Nested loops**: an outer loop for "play again", an inner loop for guessing
- `if / else if / else` chains and `switch`
- **Constants** (`static final`)
- Splitting a program into focused methods (`chooseDifficulty`, `playRound`, `rate`)
- `continue` and early `return` to keep code flat and readable

## Study guide

1. Notice that invalid input (`continue`) does not increase `attempts`. Why does that matter for fairness?
2. `RANDOM.nextInt(max) + 1`: work out why the `+ 1` is needed.
3. Read `rate()`: a perfect player using **binary search** needs at most `ceil(log2(max))` guesses.
   This is your first taste of algorithmic thinking.

## Try it yourself (extensions)

- Limit the number of attempts and show "You lose" with the answer.
- Store a high score (fewest attempts) per difficulty level.
- Let the *computer* guess *your* number using binary search.
