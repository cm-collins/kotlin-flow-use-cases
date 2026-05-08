# Kotlin Flow Use Cases

[![Repository](https://img.shields.io/badge/GitHub-Repository-blue?logo=github)](https://github.com/cm-collins/kotlin-flow-use-cases)

A practical Kotlin project that explains how to use **Flow**, **StateFlow**, **SharedFlow**, and common Flow operators in real-world scenarios.

This project is a **Pure Kotlin/JVM** environment, designed for developers to run standalone examples directly in their terminal to learn and practice.

---

## 🚀 How to Run
Every example file in this repository is **standalone**. 
1. Open any `.kt` file in the `flow_basics`, `stateflow_use_cases`, or other directories.
2. Click the green **"Play"** icon next to the `fun main()` function.
3. View the results directly in the IDE terminal.

---

## What This Project Covers
- **Flow Basics**: Understanding Cold Flows, Producers, and Consumers.
- **StateFlow**: Hot flows for State Management.
- **SharedFlow**: (Coming Soon) Hot flows for Events.
- **Operators**: (Coming Soon) Transforming, Filtering, and Buffering data.

---

## 🗺️ Learning Path
Follow this order to master Kotlin Flows:

1.  **[Flow Basics](./kotlinflows/src/main/kotlin/com/example/kotlinflows/flow_basics/README.md)**: Start here to understand Cold Flows, emitters, and collectors.
2.  **[StateFlow](./kotlinflows/src/main/kotlin/com/example/kotlinflows/stateflow_use_cases/README.md)**: Master Hot Flows for UI State Management.
3.  **Intermediate Operators**: (Coming Soon) Learn how to transform and combine data.
4.  **SharedFlow**: (Coming Soon) Master Hot Flows for one-time Events.

---

## Project Structure

```text
kotlin-flow-use-cases/
│
├── kotlinflows/             # Main Kotlin Module
│   └── src/main/kotlin/com/example/kotlinflows/
│       ├── flow_basics/     # 📍 Start Here: Cold Flow Fundamentals
│       ├── stateflow_use_cases/# ⚡ State Management
│       ├── core/            # Shared models and utilities
│       └── flow_operators/  # (Coming Soon) Intermediate techniques
│
└── README.md
```
