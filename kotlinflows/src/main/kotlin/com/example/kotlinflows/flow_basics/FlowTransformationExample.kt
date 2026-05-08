package com.example.kotlinflows.flow_basics

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

/**
 * FLOW TRANSFORMATION EXAMPLES
 * 
 * CIRCUMSTANCE OF USE:
 * Use transformation operators to modify, filter, or expand the stream of 
 * data before it reaches the collector. This keeps your business logic 
 * clean and separated from the raw data source.
 */

fun originalNumbers(): Flow<Int> = flow {
    emit(1)
    emit(2)
    emit(3)
    emit(4)
    emit(5)
}

fun main() = runBlocking {
    println("=== FLOW TRANSFORMATIONS ===")

    // 1. map: Transforms each value emitted by the flow
    println("\n--- map (Doubling numbers) ---")
    originalNumbers()
        .map { it * 2 }
        .collect { println("Mapped Value: $it") }

    // 2. filter: Filters values based on a condition
    println("\n--- filter (Only even numbers) ---")
    originalNumbers()
        .filter { it % 2 == 0 }
        .collect { println("Filtered Value: $it") }

    // 3. transform: The most flexible operator. 
    // It can emit multiple values or skip values entirely.
    println("\n--- transform (Expanding values) ---")
    originalNumbers()
        .transform { value ->
            if (value % 2 != 0) {
                emit("Odd: $value")
                emit("Extra note for $value")
            }
        }
        .collect { println("Transformed: $it") }

    // 4. take: Limits the size of the flow
    println("\n--- take (First 3 only) ---")
    originalNumbers()
        .take(3)
        .collect { println("Taken Value: $it") }

    // 5. drop: Skips the first N values
    println("\n--- drop (Skip first 2) ---")
    originalNumbers()
        .drop(2)
        .collect { println("Dropped Value: $it") }

    println("\n============================")
}
