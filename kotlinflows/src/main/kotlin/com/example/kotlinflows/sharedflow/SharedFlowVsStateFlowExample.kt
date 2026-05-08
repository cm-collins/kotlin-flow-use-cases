package com.example.kotlinflows

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * SHAREDFLOW VS STATEFLOW EXAMPLE
 *
 * CIRCUMSTANCE OF USE:
 * Use this example to understand when to use StateFlow and when to use SharedFlow.
 *
 * Main rule:
 *
 * StateFlow = current state
 * SharedFlow = one-time events or shared events
 *
 * Examples:
 *
 * StateFlow:
 * - Loading state
 * - Current user profile
 * - Current search query
 * - Current selected item
 *
 * SharedFlow:
 * - Show snackbar
 * - Navigate to another screen
 * - Show toast
 * - Open dialog
 */

/**
 * This represents the current screen state.
 *
 * StateFlow is correct here because the screen always needs to know
 * the latest state.
 */
sealed interface LoginUiState {

    data object Idle : LoginUiState

    data object Loading : LoginUiState

    data class Success(
        val userName: String
    ) : LoginUiState

    data class Error(
        val message: String
    ) : LoginUiState
}

/**
 * This represents one-time UI events.
 *
 * SharedFlow is correct here because these actions should happen once.
 */
sealed interface LoginEvent {

    data class ShowSnackbar(
        val message: String
    ) : LoginEvent

    data object NavigateToHome : LoginEvent
}

/**
 * Private MutableStateFlow.
 *
 * This stores the current screen state.
 * It must have an initial value.
 */
private val _loginUiState = MutableStateFlow<LoginUiState>(LoginUiState.Idle)

/**
 * Public read-only StateFlow.
 *
 * Other parts of the app can observe the state,
 * but they cannot change it directly.
 */
val loginUiState: StateFlow<LoginUiState> = _loginUiState

/**
 * Private MutableSharedFlow.
 *
 * This sends one-time events.
 * It does not need an initial value.
 */
private val _loginEvents = MutableSharedFlow<LoginEvent>()

/**
 * Public read-only SharedFlow.
 *
 * Other parts of the app can observe events,
 * but they cannot emit them directly.
 */
val loginEvents: SharedFlow<LoginEvent> = _loginEvents

fun main() = runBlocking {
    println("=== SHAREDFLOW VS STATEFLOW EXAMPLE ===")

    /**
     * State collector.
     *
     * This simulates the UI observing the current screen state.
     *
     * Because this is StateFlow, the first emitted value is the initial value:
     * LoginUiState.Idle
     */
    val stateCollectorJob = launch {
        loginUiState.collect { state ->
            when (state) {
                is LoginUiState.Idle -> {
                    println("STATE: Login screen is idle")
                }

                is LoginUiState.Loading -> {
                    println("STATE: Showing loading spinner")
                }

                is LoginUiState.Success -> {
                    println("STATE: Welcome ${state.userName}")
                }

                is LoginUiState.Error -> {
                    println("STATE: Showing error state: ${state.message}")
                }
            }
        }
    }

    /**
     * Event collector.
     *
     * This simulates the UI listening for one-time actions.
     *
     * Because this is SharedFlow, there is no initial value.
     * It only receives something when emit() is called.
     */
    val eventCollectorJob = launch {
        loginEvents.collect { event ->
            when (event) {
                is LoginEvent.ShowSnackbar -> {
                    println("EVENT: Show snackbar: ${event.message}")
                }

                is LoginEvent.NavigateToHome -> {
                    println("EVENT: Navigate to home screen")
                }
            }
        }
    }

    delay(1000)

    /**
     * User taps login.
     *
     * This changes the current screen state to Loading.
     */
    println("\nUser taps login button")
    _loginUiState.value = LoginUiState.Loading

    delay(1000)

    /**
     * Login succeeds.
     *
     * We update the state to Success because the screen now has successful data.
     *
     * We also emit a navigation event because navigation is an action
     * that should happen once.
     */
    println("\nLogin succeeds")
    _loginUiState.value = LoginUiState.Success(
        userName = "Collins Munene"
    )

    _loginEvents.emit(LoginEvent.NavigateToHome)

    delay(1000)

    /**
     * Later, something fails.
     *
     * Error state describes what the UI should display now,
     * so we use StateFlow.
     *
     * Snackbar is a one-time message,
     * so we use SharedFlow.
     */
    println("\nA refresh fails")
    _loginUiState.value = LoginUiState.Error(
        message = "Failed to refresh user profile"
    )

    _loginEvents.emit(
        LoginEvent.ShowSnackbar("Please check your internet connection")
    )

    delay(1000)

    stateCollectorJob.cancel()
    eventCollectorJob.cancel()

    println("\n=== DEMO COMPLETED ===")
}