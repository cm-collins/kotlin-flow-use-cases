package com.example.kotlinflows.flow_basics

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking

/**
 * FLOW COLLECTION EXAMPLE
 * 
 * CIRCUMSTANCE OF USE:
 * Use .collect() when you want to start the Flow and process each value 
 * emitted by the producer. It is the most common terminal operator.
 */

// A simple producer that emits a sequence of tens
fun numbersFlow(): Flow<Int> = flow {
    emit(10)
    emit(20)
    emit(30)
    emit(40)
}

fun main() = runBlocking {
    println("--- Flow Collection (Terminal Operator) ---")
    
    /**
     * .collect() is a suspending function. 
     * It keeps the coroutine active until the Flow is finished.
     */
    numbersFlow().collect { value -> 
        println("Collected Value: $value") 
    }
    
    println("\nFlow collection complete.")
}
