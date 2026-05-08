# Kotlin Flow Use Cases

[![Repository](https://img.shields.io/badge/GitHub-Repository-blue?logo=github)](https://github.com/cm-collins/kotlin-flow-use-cases)

A practical Kotlin and Android demo project that explains how to use **Flow**, **StateFlow**, **SharedFlow**, and common Flow operators in real-world scenarios.

This project is built for Android and Kotlin developers who want to understand how Kotlin Flows work in clean, simple, and practical examples.

---

## What This Project Covers

This repository demonstrates how to use:

- `Flow`
- `StateFlow`
- `SharedFlow`
- `buffer()`
- `conflate()`
- `debounce()`
- `combine()`
- `map()`
- `filter()`
- `catch()`
- `onEach()`

Each example focuses on a specific use case and explains where the Flow type or operator is useful.

---

## Why This Project Exists

Kotlin Flow is powerful, but it can be confusing when learning it for the first time.

Many developers understand the theory but struggle to know:

- When to use `Flow`
- When to use `StateFlow`
- When to use `SharedFlow`
- Where operators like `buffer`, `conflate`, and `debounce` are useful
- How Flow works in Android apps
- How to structure Flow usage in a clean architecture project

This project solves that by providing simple, focused, and practical examples.

---

## Repository Goal

The goal of this repository is to make Kotlin Flow easier to understand through real use cases.

Instead of only explaining concepts, this project shows how they work in actual code.

The project is useful for:

- Android engineers learning Kotlin Flow
- Developers preparing for interviews
- Engineers writing cleaner reactive Android apps
- Anyone building apps with Kotlin Coroutines and Jetpack Compose
- Developers who want simple examples before using Flow in production

---

## Project Structure

```text
kotlin-flow-use-cases/
│
├── app/
│   └── Main Android application
│
├── core/
│   └── Shared models, utilities, and common classes
│
├── flow-basics/
│   └── Basic Flow examples
│
├── stateflow-use-cases/
│   └── UI state management examples
│
├── sharedflow-use-cases/
│   └── One-time event examples
│
├── flow-operators/
│   ├── buffer-demo/
│   ├── conflate-demo/
│   ├── debounce-demo/
│   ├── combine-demo/
│   ├── map-filter-demo/
│   └── catch-demo/
│
└── README.md
```
