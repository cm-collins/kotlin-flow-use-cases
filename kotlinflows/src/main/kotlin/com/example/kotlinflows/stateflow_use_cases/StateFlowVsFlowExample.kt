package com.example.kotlinflows.stateflow_use_cases

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * STATEFLOW VS FLOW EXAMPLE
 *
 * CIRCUMSTANCE OF USE:
 * Use this example when you want to understand the difference between
 * a normal Flow and a StateFlow.
 *
 * The main difference:
 *
 * Flow:
 * - Cold
 * - Does not start until collected
 * - Runs again for every new collector
 * - Does not store the latest value
 *
 * StateFlow:
 * - Hot
 * - Always has a current value
 * - Immediately gives the latest value to collectors
 * - Good for state
 */

/**
 * Normal Flow.
 *
 * This Flow is cold.
 *
 * The code inside flow { } will only run when collect() is called.
 */
fun normalColdFlow(): Flow<String> = flow {
    println("[Flow] Started")

    emit("Loading")
    delay(1000)

    emit("Success")
    delay(1000)

    emit("Completed")

    println("[Flow] Finished")
}

/**
 * StateFlow.
 *
 * This StateFlow is hot.
 *
 * It already has a value before anyone collects it.
 */
private val _stateFlow = MutableStateFlow("Initial State")

val stateFlow: StateFlow<String> = _stateFlow

fun main() = runBlocking {
    println("=== STATEFLOW VS FLOW EXAMPLE ===")

    println("\n--- Normal Flow Example ---")

    /**
     * Collecting the normal Flow for the first time.
     *
     * This starts the Flow.
     */
    normalColdFlow().collect { value ->
        println("Collector 1 received from Flow: $value")
    }

    println("\nCollecting the same Flow again")

    /**
     * Collecting again starts the Flow again from the beginning.
     */
    normalColdFlow().collect { value ->
        println("Collector 2 received from Flow: $value")
    }

    println("\n--- StateFlow Example ---")

    /**
     * Updating StateFlow before collecting.
     *
     * This is possible because StateFlow already exists in memory
     * and always stores the latest value.
     */
    _stateFlow.value = "Latest State Before Collection"

    /**
     * Collector 1 starts collecting.
     *
     * It immediately receives the latest value:
     * "Latest State Before Collection"
     */
    val collectorOne = launch {
        stateFlow.collect { value ->
            println("Collector 1 received from StateFlow: $value")
        }
    }

    delay(1000)

    /**
     * Update StateFlow again.
     *
     * Active collectors will receive this new value.
     */
    _stateFlow.value = "Updated State"

    delay(1000)

    /**
     * Collector 2 starts late.
     *
     * It does not receive old values.
     * It immediately receives the latest value only.
     */
    val collectorTwo = launch {
        stateFlow.collect { value ->
            println("Collector 2 received from StateFlow: $value")
        }
    }

    delay(1000)

    _stateFlow.value = "Final State"

    delay(1000)

    /**
     * StateFlow keeps collecting because it represents ongoing state.
     *
     * We cancel collectors manually because this is a standalone demo.
     */
    collectorOne.cancel()
    collectorTwo.cancel()

    println("=== DEMO COMPLETED ===")
}