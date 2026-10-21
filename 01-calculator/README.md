# 01 - Calculator

A menu-driven command-line calculator (add, subtract, multiply, divide).

## Run

```bash
javac Calculator.java
java Calculator
```

## What you practice

- Writing small, single-purpose **methods**
- **Control flow**: `while`, `if`, `switch` expressions, `break` / `continue`
- Reading input with **`Scanner`**
- **Input validation** with `try/catch` around `Double.parseDouble`
- Throwing and catching an **exception** (`ArithmeticException` for divide-by-zero)

## Study guide

1. Read `main` first: it is just a loop that shows a menu and dispatches to a method.
2. Look at `readNumber`: why a `while (true)` loop with `return` inside is a common pattern for validated input.
3. Look at `divide`: why check for zero explicitly instead of relying on Java's behaviour (`1.0 / 0` is `Infinity`, not an error)?

## Try it yourself (extensions)

- Add modulus (`%`) and power operations.
- Let the user type an expression like `12 + 5` on one line.
- Keep a history of the last 5 calculations.
- Replace `double` with `BigDecimal` and find out why money calculations use it.
