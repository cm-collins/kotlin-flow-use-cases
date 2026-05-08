package com.example.kotlinflows.stateflow_use_cases

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * MUTABLE STATEFLOW EXAMPLE
 *
 * CIRCUMSTANCE OF USE:
 * Use MutableStateFlow when you need to change/update state over time.
 *
 * StateFlow is for reading state.
 * MutableStateFlow is for changing state.
 *
 * In real Android apps, this pattern is common in ViewModels:
 *
 * private val _counterState = MutableStateFlow(0)
 * val counterState: StateFlow<Int> = _counterState
 *
 * The private MutableStateFlow can be updated inside the ViewModel.
 * The public StateFlow can only be observed by the UI.
 */

/**
 * Private mutable state.
 *
 * This can be changed only inside this file/class.
 */

private val _counterstate = MutableStateFlow(0)

/**
 * Public read-only state.
 *
 * Other parts of the app can observe this,
 * but they cannot directly change its value.
 */

val counterstate: StateFlow<Int> = _counterstate

fun main() = runBlocking {
    println("=== MUTABLE STATEFLOW EXAMPLE ===")
    /**
     * Start collecting the current counter value.
     *
     * StateFlow immediately emits the initial value.
     * In this case, the first value will be 0.
     */

    val collectorjob = launch {
        counterstate.collect { count ->
            println("Current Count: $count")
        }
    }

    delay(1000)
    /**
     * Method 1: Update using .value
     *
     * This replaces the current value with a new value.
     */
    println("Increment using .value")
    _counterstate.value = _counterstate.value + 1
    delay(1000)
    /**
     * Method 2: Update using update { }
     *
     * This is a clean and safe way to update based on the current value.
     *
     * currentValue represents the latest value inside the StateFlow.
     */
    println("Increment using update { }")
    _counterstate.update { currentvalue -> currentvalue + 1 }
    delay(1000)
    println("Reset counter to 0")
    _counterstate.value = 0
    delay(1000)
    /**
     * StateFlow keeps collecting until we cancel it.
     *
     * We cancel here only because this is a standalone demo.
     */
    collectorjob.cancel()
    println("===DEMO COMPLETED ==")

}