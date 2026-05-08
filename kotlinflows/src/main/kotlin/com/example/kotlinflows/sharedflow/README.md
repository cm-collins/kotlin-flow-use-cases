# SharedFlow Use Cases

This section explains how to use **SharedFlow** in Kotlin.

`SharedFlow` is used when you want to send values or events to one or more collectors.

It is commonly used for events that should be observed by the UI or by different parts of an application.

---

## What is SharedFlow?

`SharedFlow` is a hot Flow.

This means it can emit values even when it already exists in memory.

Unlike `StateFlow`, a `SharedFlow` does not need an initial value.

Simple meaning:

```text
SharedFlow = a Flow used to share events or values with collectors