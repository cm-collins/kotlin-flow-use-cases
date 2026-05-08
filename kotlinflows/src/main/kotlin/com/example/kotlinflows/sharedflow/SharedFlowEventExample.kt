package com.example.kotlinflows.sharedflow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * SHAREDFLOW EVENT EXAMPLE
 *
 * CIRCUMSTANCE OF USE:
 * Use SharedFlow when you want to send one-time events to the UI.
 *
 * One-time events are actions that should happen once.
 *
 * Examples:
 * - Show snackbar
 * - Show toast
 * - Navigate to another screen
 * - Open dialog
 *
 * These are not UI states.
 * They are UI actions/events.
 */

/**
 * A sealed interface helps us define all possible events clearly.
 *
 * In this example, the UI can receive:
 * - ShowSnackbar
 * - ShowToast
 * - NavigateToProfile
 */
sealed interface UiEvent {
    data class ShowSnackbar(
        val message: String
    ) : UiEvent

    data class ShowToast(
        val message: String
    ) : UiEvent

    data object NavigateToProfile : UiEvent
}

/**
 * Private MutableSharedFlow.
 *
 * This is used to send UI events.
 */
private val _uiEvents = MutableSharedFlow<UiEvent>()

/**
 * Public read-only SharedFlow.
 *
 * Other parts of the app can collect events,
 * but they cannot emit events directly.
 */

val uiEvents: SharedFlow<UiEvent> = _uiEvents

fun main() = runBlocking {
    println("=== SHAREDFLOW EVENT EXAMPLE ===")
    /**
     * Collector.
     *
     * This simulates the UI listening for one-time events.
     */

    val collectorJob = launch {
        uiEvents.collect { event ->
            when (event) {
                is UiEvent.ShowSnackbar -> {
                    println("UI Action: Show snackbar")
                    println("Message: ${event.message}")

                }

                is UiEvent.ShowToast -> {
                    println("UI Action: Show toast")
                    println("Message: ${event.message}")

                }

                is UiEvent.NavigateToProfile -> {
                    println("UI Action: Navigate to profile screen")
                }

            }

        }
    }

    delay(1000)
    /**
     * Send a snackbar event.
     */
    println("Sending snackbar event")
    _uiEvents.emit(
        UiEvent.ShowSnackbar("Profile Saved successfully")
    )
    delay(1000)
    /**
     * Send a toast event.
     */
    _uiEvents.emit(
        UiEvent.ShowToast("Welcome Back")
    )
    /**
     * Send a navigation event.
     */
    println("Sending navigation Event")
    _uiEvents.emit(UiEvent.NavigateToProfile)
    delay(1000)
    /**
     * Cancel only because this is a standalone demo.
     */
    collectorJob.cancel()
    println("=== DEMO COMPLETED ===")




}