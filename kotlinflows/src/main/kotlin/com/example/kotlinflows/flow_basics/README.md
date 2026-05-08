# 📍 Flow Basics

This module covers the fundamental concepts of **Cold Flows** in Kotlin.

## ❓ What is a Simple Flow?
A `Flow` is a stream of values that are computed asynchronously. Unlike a `List` which stores all values in memory, a `Flow` produces values one-by-one only when they are needed.

### Key Characteristics:
1. **Asynchronous**: It can emit values over time (e.g., waiting for a network response) without blocking the main thread.
2. **Cold**: The code inside a `flow { ... }` builder doesn't run until someone calls `.collect()`. Each new collector triggers the code to run from the beginning.
3. **Sequential**: Values are processed one after the other.

---

## 🏗️ Production Use Cases
When should you use a simple `Flow` in a real app?

1. **Database Queries (Room)**:
   - Observation: When you query a database, you get a `Flow`. Every time the data in the database changes, the Flow automatically emits the new list of items.
   
2. **Network Polling**:
   - If you need to refresh a stock price or a weather update every 30 seconds, a `Flow` can emit the new value on each interval.

3. **Sensor Data**:
   - Tracking GPS location, accelerometer data, or pedometer steps where values are constantly arriving.

4. **File Upload/Download**:
   - Emitting the percentage progress (1%, 2%, ... 100%) as a file is being processed.

---

## 🛠️ Essential Concepts in this Module

- **[BasicFlowExamples.kt](./BasicFlowExamples.kt)**: The simplest way to create and collect a flow.
- **[ColdFlowExample.kt](./ColdFlowExample.kt)**: Understanding why Flows are "Cold" and how they behave with multiple collectors.
- **[FlowCollectionExample.kt](./FlowCollectionExample.kt)**: Exploring Terminal Operators like `.collect()`, `.first()`, and `.toList()`.
- **[FlowTransformationExample.kt](./FlowTransformationExample.kt)**: How to clean and modify data using `.map()` and `.filter()`.
- **[FlowErrorExample.kt](./FlowErrorExample.kt)**: How to safely catch errors so your app doesn't crash.
- **[FakeUserRepository.kt](./FakeUserRepository.kt)**: A real-world simulation of how a Data Layer provides flows to a UI.

---

## 📚 Official Documentation
For a deeper dive into the theory, check out the official resources:
- [Kotlin Flow Official Guide](https://kotlinlang.org/docs/flow.html)
- [Android Developers: Kotlin Flow on Android](https://developer.android.com/kotlin/flow)

---

## 🚦 Summary: How it works
1. **Producer**: The `flow { ... }` block that `emit()`s values.
2. **Intermediary**: Operators like `.map` that change the data.
3. **Consumer**: The `.collect { ... }` block that finally uses the data.
