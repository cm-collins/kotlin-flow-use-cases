package com.example.kotlinflows.stateflow_use_cases

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * STATEFLOW SEARCH EXAMPLE
 *
 * CIRCUMSTANCE OF USE:
 * Use StateFlow when you want to keep track of the latest search text.
 *
 * Search text is state because:
 * - It has a current value
 * - It changes when the user types
 * - The UI needs to know the latest value
 *
 * In Android, this is commonly used with a TextField.
 */

/**
 * Private MutableStateFlow.
 *
 * This stores the current search query.
 * The initial value is an empty string because the user has not typed anything yet.
 */

private val _searchQuery = MutableStateFlow("")

/**
 * Public read-only StateFlow.
 *
 * Other parts of the app can observe the search query,
 * but they cannot change it directly.
 */

val searchQuery: StateFlow<String> = _searchQuery

/**
 * This function simulates a user typing into a search box.
 *
 * In a real Android app, this function could be called from:
 *
 * TextField(
 *     value = searchQuery,
 *     onValueChange = { viewModel.onSearchQueryChanged(it) }
 * )
 */
fun onSearchQueryChanged(query: String) {
    _searchQuery.value = query

}

fun main() = runBlocking {
    println("=== STATEFLOW SEARCH EXAMPLE ===")
    /**
     * Collector.
     *
     * This simulates the UI or ViewModel observing the latest search text.
     *
     * StateFlow immediately emits its current value.
     * The first value will be an empty string.
     */

    val collectorJob = launch {
        searchQuery.collect { query ->
            if (query.isBlank()) {
                println("Search query is empty")

            } else {
                println("Current Search query: $query")

            }
        }
    }

    delay(1000)
    println("User types: k")
    onSearchQueryChanged("k")

    delay(500)

    println("User types: ko")
    onSearchQueryChanged("ko")

    delay(500)

    println("User types: kot")
    onSearchQueryChanged("kot")

    delay(500)

    println("User types: kotl")
    onSearchQueryChanged("kotl")

    delay(500)

    println("User types: kotlin")
    onSearchQueryChanged("kotlin")

    delay(1000)

    /**
     * We cancel the collector only because this is a standalone demo.
     */
    collectorJob.cancel()

    println("=== DEMO COMPLETED ===")}