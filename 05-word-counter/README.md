# 05 - Word Counter

Counts the lines, words and characters in a text file, similar to the Unix `wc` command.

## Run

```bash
javac WordCounter.java
java WordCounter sample.txt
```

Or run `java WordCounter` and type the file path when asked.

## What you practice

- **File I/O** with `BufferedReader` / `FileReader`
- **try-with-resources** (automatic closing of files)
- **Checked exceptions** (`IOException`) and why Java forces you to handle them
- **Command-line arguments** (`args[0]`)
- **String manipulation**: `trim()`, `isEmpty()`, `split("\\s+")`
- **Records** as lightweight data holders

## Study guide

1. Why does `split("\\s+")` work better than `split(" ")` when there are multiple spaces or tabs?
2. Why do we check `!trimmed.isEmpty()` before splitting? (Try `"".split("\\s+").length`: it returns 1, not 0!)
3. Remove the try-with-resources and use a plain `try/finally` to close the file. Notice how much longer it gets.

## Try it yourself (extensions)

- Show the most frequent word using a `HashMap<String, Integer>`.
- Accept several file paths and print totals.
- Add a `-w` flag so it prints only the word count.
