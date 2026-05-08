package com.example.kotlinflows.stateflow_use_cases

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * STATEFLOW UI STATE EXAMPLE
 *
 * CIRCUMSTANCE OF USE:
 * Use StateFlow when a screen needs to observe its current UI state.
 *
 * A screen is usually in one of these states:
 * - Loading: data is being fetched
 * - Success: data was loaded successfully
 * - Error: something went wrong
 *
 * This is one of the most common ways StateFlow is used in Android ViewModels.
 */

/**
 * A sealed interface is useful when a value can only be one of a few known types.
 *
 * In this example, the UI state can only be:
 * - Loading
 * - Success
 * - Error
 */

sealed interface ProfileUiState {
    data object Loading : ProfileUiState

    data class Success(
        val userName: String, val email: String
    ) : ProfileUiState

    data class Error(
        val message: String
    ) : ProfileUiState
}

/**
 * Private MutableStateFlow.
 *
 * This holds the current screen state and can be updated.
 */
private val _profileUiState = MutableStateFlow<ProfileUiState>(ProfileUiState.Loading)

/**
 * Public read-only StateFlow.
 *
 * Other parts of the app can observe this,
 * but they cannot update it directly.
 */
val profileUiState: StateFlow<ProfileUiState> = _profileUiState

fun main() = runBlocking {
    println("=== STATEFLOW UI STATE EXAMPLE ===")

    /**
     * Collector.
     *
     * This simulates the UI observing the screen state.
     */
    val collectorJob = launch {
        profileUiState.collect { state ->
            when (state) {
                is ProfileUiState.Loading -> {
                    println("UI: Showing loading spinner...")
                }

                is ProfileUiState.Success -> {
                    println("UI: Showing profile")
                    println("Name: ${state.userName}")
                    println("Email: ${state.email}")
                }

                is ProfileUiState.Error -> {
                    println("UI: Showing error message")
                    println("Error: ${state.message}")
                }
            }
        }
    }

    delay(1000)

    /**
     * Simulate a successful data fetch.
     */
    _profileUiState.value = ProfileUiState.Success(
        userName = "Collins Munene",
        email = "collins@example.com"
    )

    delay(2000)

    /**
     * Simulate an error during refresh.
     *
     * This helps us see how the UI reacts when state changes.
     */
    _profileUiState.value = ProfileUiState.Error(
        message = "Failed to refresh profile"
    )

    delay(1000)
    collectorJob.cancel()
    println("=== DEMO COMPLETED ===")
}
