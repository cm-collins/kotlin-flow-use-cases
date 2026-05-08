# StateFlow Use Cases

This section explains how to use **StateFlow** in Kotlin.

`StateFlow` is used when you want to hold and observe the **latest state** of something.

It is commonly used in Android apps to expose UI state from a ViewModel to the screen.

---

## What is StateFlow?

`StateFlow` is a hot Flow that always has a current value.

This means:

- It starts with an initial value.
- It always keeps the latest value in memory.
- New collectors immediately receive the latest value upon subscription.
- It is ideal for representing UI state.

---

## Examples in this Section

- **[BasicStateFlowExample.kt](./BasicStateFlowExample.kt)**: The simplest way to create and collect a StateFlow.
- **[MutableStateFlowExample.kt](./MutableStateFlowExample.kt)**: Shows how to update state using `MutableStateFlow`.
- **[StateFlowVsFlowExample.kt](./StateFlowVsFlowExample.kt)**: A side-by-side comparison between Cold Flows and Hot StateFlows.
- **[StateFlowUiStateExample.kt](./StateFlowUiStateExample.kt)**: A real-world scenario showing how to manage `Loading`, `Success`, and `Error` states.
- **[StateFlowSearchExample.kt](./StateFlowSearchExample.kt)**: Demonstrates using StateFlow for real-time search filtering.

---

## Key Takeaway

> **StateFlow = a Flow that always remembers the latest value.**
