package com.example.kotlinflows.stateflow_use_cases

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * BASIC STATEFLOW EXAMPLE
 *
 * CIRCUMSTANCE OF USE:
 * Use StateFlow when you want to hold and observe the latest state of something.
 *
 * A normal Flow emits values when collected.
 * A StateFlow always has a current value.
 *
 * Example use cases:
 * - Current screen state
 * - Current user name
 * - Current login status
 * - Current loading state
 * - Current search text
 */

/**
 * MutableStateFlow is used when we want to update the value.
 *
 * It requires an initial value.
 *
 * In this example, the initial value is "Loading".
 */

private val  _screenState = MutableStateFlow("Loading")

/**
 * StateFlow is the read-only version.
 *
 * Other parts of the app can collect this value,
 * but they cannot change it directly.
 *
 * This is the same pattern commonly used in Android ViewModels.
 */

val screenState: StateFlow<String> = _screenState
fun main() = runBlocking {
    println("=== BASIC STATEFLOW EXAMPLE ===")

    /**
     * Collector
     *
     * This listens to the StateFlow.
     *
     * Important:
     * When collection starts, StateFlow immediately gives the latest value.
     *
     * Since the initial value is "Loading",
     * the first printed value will be "Loading".
     */
    val collectorJob = launch {
        screenState.collect { state ->
            println("Current Screen State: $state")
        }
    }

    delay(1000)
    /**
     * Updating the StateFlow value.
     *
     * When we assign a new value to _screenState.value,
     * every active collector receives the new value.
     */

    _screenState.value = "Fetching user data"
    delay(1000)
    _screenState.value = "User data loaded successfully"
    delay(1000)
    _screenState.value = "Displaying user profile"
    delay(1000)

    /**
     * StateFlow keeps running because it is designed to represent state.
     *
     * In this demo, we cancel the collector manually
     * so the program can finish.
     */

    collectorJob.cancel()

    println("===DEMO COMPLETED===")
}