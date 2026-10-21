# 04 - Password Generator

Generates random passwords based on length and character-type options, and estimates their strength.

## Run

```bash
javac PasswordGenerator.java
java PasswordGenerator
```

## What you practice

- **`SecureRandom`** vs `Random` (security-sensitive code must use the former)
- **`StringBuilder`** for efficient string building
- **Collections**: `List<Character>`, `Collections.shuffle`
- Designing an algorithm in steps (guarantee -> fill -> shuffle)
- Throwing `IllegalArgumentException` for invalid arguments
- Basic **entropy** maths: `bits = length * log2(poolSize)`

## Study guide

1. Why do we guarantee one character per category *before* filling the rest? What would go wrong if we
   only picked random characters from the pool? (Sometimes no digit would appear even though the user asked for one.)
2. Why shuffle at the end? What would the first few characters always look like otherwise?
3. Why is `java.util.Random` unsuitable for passwords? Search for "predictable PRNG".

## Try it yourself (extensions)

- Exclude look-alike characters (`0 O 1 l I`).
- Generate a passphrase from random words instead of random characters.
- Generate and print 5 passwords at once.
