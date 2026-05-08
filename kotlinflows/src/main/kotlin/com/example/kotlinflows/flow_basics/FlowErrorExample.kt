package com.example.kotlinflows.flow_basics

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking

/**
 * FLOW ERROR HANDLING EXAMPLE
 * 
 * CIRCUMSTANCE OF USE:
 * Use the .catch { } operator to handle exceptions that occur during
 * flow emission. This ensures your app doesn't crash and allows you 
 * to provide fallback values or log errors safely.
 */

// A flow that intentionally throws an error after a few emissions.
fun failingFlow(): Flow<Int> = flow {
    emit(1)
    emit(2)
    
    println("Checking logic... Something is wrong!")
    // Simulating a real-world error (e.g., a network timeout or DB failure)
    throw IllegalStateException("Connection Lost")
    
    // This will never be executed
    emit(3)
}

/**
 * HOW IT WORKS:
 * 1. The .catch operator catches exceptions that happen UPSTREAM 
 *    (in the flow builder or previous operators).
 * 2. It DOES NOT catch exceptions that happen in the .collect { } block.
 * 3. Inside .catch, you can:
 *    - Log the error.
 *    - Re-throw the error (to be handled elsewhere).
 *    - Emit a "fallback" value to keep the stream alive or show a default state.
 */
fun main() = runBlocking {
    println("=== STARTING ERROR HANDLING DEMO ===")

    failingFlow()
        .catch { error -> 
            // 1. Handle the error (Log it)
            println("Caught Exception: ${error.message}")
            
            // 2. Provide a fallback value
            println("Emitting fallback value: -1")
            emit(-1) 
        }
        .collect { value ->
            println("Received in UI: $value")
        }

    println("\n=== FLOW COMPLETED SAFELY ===")
}
