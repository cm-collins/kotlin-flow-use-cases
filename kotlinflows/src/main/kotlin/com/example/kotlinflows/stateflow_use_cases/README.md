# StateFlow Use Cases

This section explains how to use **StateFlow** in Kotlin.

`StateFlow` is used when you want to hold and observe the **latest state** of something.

It is commonly used in Android apps to expose UI state from a ViewModel to the screen.

---

## What is StateFlow?

`StateFlow` is a hot Flow that always has a current value.

This means:

- It starts with an initial value
- It always keeps the latest value
- New collectors immediately receive the latest value
- It is useful for representing state

Simple meaning:

```text
StateFlow = a Flow that always remembers the latest value