package com.example.kotlinflows.flow_basics

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

/**
 * FLOW COLLECTION & TERMINAL OPERATORS
 * 
 * CIRCUMSTANCE OF USE:
 * Flows are cold, meaning they don't do anything until a "Terminal Operator" 
 * is called. While .collect() is the most common, there are others that 
 * transform the flow into a single value or a list.
 */

fun numbersFlow(): Flow<Int> = flow {
    emit(10)
    emit(20)
    emit(30)
}

fun main() = runBlocking {
    println("=== TERMINAL OPERATORS DEMO ===")

    // 1. collect: The most basic way to receive all values
    println("\n--- .collect() ---")
    numbersFlow().collect { value -> 
        println("Collected: $value") 
    }

    // 2. first: Gets only the first value and cancels the rest
    println("\n--- .first() ---")
    val firstValue = numbersFlow().first()
    println("First Value only: $firstValue")

    // 3. toList: Collects all values and puts them into a List
    println("\n--- .toList() ---")
    val list = numbersFlow().toList()
    println("Converted to List: $list")

    // 4. reduce: Accumulates values (e.g., calculating a sum)
    println("\n--- .reduce() (Summing values) ---")
    val sum = numbersFlow().reduce { accumulator, value -> 
        accumulator + value 
    }
    println("Sum of all values: $sum")

    // 5. count: Counts how many items match a condition
    println("\n--- .count() (Items > 15) ---")
    val count = numbersFlow().count { it > 15 }
    println("Count: $count")

    println("\n=== DEMO COMPLETE ===")
}
