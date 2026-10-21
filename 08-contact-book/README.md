# 08 - Contact Book

Add, view, search and delete contacts. Contacts are kept sorted by name and looked up case-insensitively.

## Run

```bash
javac ContactBook.java
java ContactBook
```

## What you practice

- **`Map`** (key -> value) with `TreeMap` (sorted keys) and why a map beats a list for lookups
- **Records** for data and a **class** for behaviour
- **Regular expressions** for phone and email validation
- **Sorting** with `Comparator.comparing`
- Returning `boolean` / `null` / `List` to signal outcomes from storage methods
- **Layered design**: storage methods (`add`, `delete`, `find`, `search`) are separate from the user
  interface (`main`, `addContactFlow`, `printContacts`)

## Study guide

1. Why is the map key `name.toLowerCase()` instead of the name itself? What breaks otherwise?
2. Compare `find` (O(log n) in a TreeMap, O(1) in a HashMap) with `search` (must scan everything).
   When would you choose a `HashMap` instead of a `TreeMap`?
3. Since the storage layer has no `Scanner` or `System.out`, you could reuse it in a GUI or web app
   without changes. This is the real payoff of layering your code.

## Try it yourself (extensions)

- Persist contacts to a CSV file (reuse file I/O from project 05).
- Add an "edit contact" option.
- Allow multiple phone numbers per contact (`List<String>`).
- Replace the `TreeMap` with a `HashMap` and sort only when displaying.
