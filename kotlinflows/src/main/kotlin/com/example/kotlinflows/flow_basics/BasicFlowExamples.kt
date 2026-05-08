package com.example.kotlinflows.flow_basics

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking

/**
 * BASIC FLOW EXAMPLES
 * 
 * CIRCUMSTANCE OF USE:
 * Use this structure when you need to emit a stream of data sequentially.
 * Flow is ideal for operations like fetching data from a database or 
 * receiving sensor updates where values arrive over time.
 */

// A simple function returning a Flow of Integers.
// The flow { ... } builder is the most basic way to create a stream.
fun simpleNumberFlow(): Flow<Int> = flow {
    emit(1) // 'emit' sends a value to the collector
    emit(2)
    emit(3)
}

// Flows are type-safe. This one only handles Strings.
fun simpleNameFlow(): Flow<String> = flow {
    emit("Collins")
    emit("Android Engineer")
    emit("Kotlin Flow learning")
}

// Example showing that flows can emit values over time with delays.
fun delayedNumberFlow(): Flow<Int> = flow {
    emit(100)
    delay(1000) // Suspend for 1 second
    emit(200)
    delay(1000)
    emit(300)
}


/**
 * Why runBlocking?
 * In a standalone Kotlin file, the main() thread would exit immediately.
 * runBlocking {} is used here to bridge the non-coroutine world (main) 
 * with the coroutine world (flow collection), keeping the program 
 * alive until all emissions are processed.
 */
fun main() = runBlocking {
    println("--- Simple Number Flow ---")
    // .collect is a terminal operator. It 'starts' the flow and 
    // waits for values to be emitted.
    simpleNumberFlow().collect { value ->
        println("Received: $value")
    }

    println("\n--- Simple Name Flow ---")
    simpleNameFlow().collect { name ->
        println("Received: $name")
    }
    
    println("\n--- Delayed Number Flow (Watch the timing) ---")
    delayedNumberFlow().collect { number -> 
        println("Received after delay: $number") 
    }
}
