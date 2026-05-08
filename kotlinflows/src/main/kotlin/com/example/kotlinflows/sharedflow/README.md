# SharedFlow Use Cases

This section explains how to use **SharedFlow** in Kotlin.

`SharedFlow` is a **hot flow** used to emit events that can be shared by multiple collectors. Unlike `StateFlow`, it does not require an initial value and is primarily used for **one-time events**.

---

## What is SharedFlow?

A `SharedFlow` is "hot" because it exists independently of its collectors. It is ideal for broadcasting events to multiple observers.

Key Characteristics:
- **No initial value**: It doesn't need a starting state.
- **Multiple collectors**: Every collector receives the values emitted after it starts collecting.
- **Replay cache**: Can be configured to "replay" a number of previous values to new collectors.
- **Buffer overflow**: Provides strategies (like `DROP_OLDEST`) for handling slow collectors.

---

## When to use SharedFlow vs StateFlow?

| Feature | StateFlow | SharedFlow |
| :--- | :--- | :--- |
| **Purpose** | To represent **State** (latest data) | To represent **Events** (actions) |
| **Initial Value** | Required | Not required |
| **Replay** | Always replays the last value | Configurable (default is 0) |
| **Usage** | UI State (Loading, Success, Error) | Navigation, SnackBar, Toasts |

---

## Examples in this Section

- **[BasicSharedFlowExample.kt](./BasicSharedFlowExample.kt)**: Introduction to creating and collecting a SharedFlow.
- **[MutableSharedFlowExample.kt](./MutableSharedFlowExample.kt)**: Using `emit()` and `tryEmit()` to send events.
- **[SharedFlowEventExample.kt](./SharedFlowEventExample.kt)**: Real-world scenario for UI events (Navigation, Notifications).
- **[SharedFlowReplayExample.kt](./SharedFlowReplayExample.kt)**: Understanding how the `replay` parameter works for late collectors.
- **[SharedFlowVsStateFlowExample.kt](./SharedFlowVsStateFlowExample.kt)**: Side-by-side comparison to help you choose the right tool.

---

## 💡 Learning Tips for Developers

1. **Events should be one-time**: If you rotate your screen in Android, you usually don't want a "Show Toast" event to fire again. Use `SharedFlow` with `replay = 0` for this.
2. **Buffering**: If you are emitting events very fast, consider the `extraBufferCapacity` parameter to avoid suspending the emitter.
3. **Testing**: Use `runTest` from `kotlinx-coroutines-test` to test your flows reliably.

---

## Further Reading
- [Official Kotlin Documentation: SharedFlow](https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/-shared-flow/)
- [Android Developers: StateFlow and SharedFlow](https://developer.android.com/kotlin/flow/stateflow-and-sharedflow)
