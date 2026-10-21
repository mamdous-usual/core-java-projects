# 03 - Unit Converter

Converts between common units: km/miles, Celsius/Fahrenheit, kg/pounds, meters/feet.

## Run

```bash
javac UnitConverter.java
java UnitConverter
```

## What you practice

- **Enums** with fields and a constructor
- **Switch expressions** that must cover every enum value (the compiler enforces it)
- **Pure functions**: `convert()` takes input, returns output, has no side effects, and is easy to test
- Named **constants** instead of magic numbers
- `printf` formatting (`%.2f`, `%d`, `%s`, `%n`)
- Building a menu dynamically by looping over `Conversion.values()`

## Study guide

1. Add a new conversion to the enum and see how the compiler forces you to handle it in `convert()`.
   That is the main advantage of enums over strings.
2. Compare the two halves: `convert()` is logic, `main()` is user interface. Keeping them apart is what
   makes code reusable (you could call `convert()` from a web app unchanged).

## Try it yourself (extensions)

- Add a new category such as liters/gallons or bytes/megabytes.
- Group the menu by category (length, temperature, weight) with a two-level menu.
- Write a tiny `main`-less test that checks `convert(CELSIUS_TO_FAHRENHEIT, 100) == 212`.
