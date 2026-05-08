package com.example.kotlinflows.flow_basics

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking

/**
 * COLD FLOW EXAMPLE
 * 
 * CIRCUMSTANCE OF USE:
 * A "Cold Flow" is like a YouTube video: it only starts playing when 
 * someone clicks 'Play' (calls .collect). Every new viewer gets their 
 * own independent stream from the beginning.
 * 
 * Use Cold Flows for most standard operations (API calls, DB queries) 
 * where you want the work to start only when there is actually an 
 * observer listening.
 */

fun coldFlow(): Flow<Int> = flow {
    // This code block is reactive. It won't run until .collect() is called.
    println("Flow Started (Executing logic inside the flow builder)")
    emit(1)
    emit(2)
    emit(3)
    println("Flow Finished")
}

fun main() = runBlocking {
    /**
     * PROOF OF COLD BEHAVIOR:
     * Notice that when we collect a second time, the "Flow Started" 
     * message prints again. This proves the logic inside the flow { } 
     * block is re-executed for every single collector.
     */

    println("--- Cold Flow (Collection 1) ---")
    coldFlow().collect { value ->
        println("Cold Value: $value")
    }

    println("\n--- Cold Flow (Collection 2) ---")
    // Collection 2 starts the whole process fresh.
    coldFlow().collect { value ->
        println("Cold Value: $value")
    }
}
