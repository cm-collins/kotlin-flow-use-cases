package com.example.kotlinflows.sharedflow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * MUTABLE SHAREDFLOW EXAMPLE
 *
 * CIRCUMSTANCE OF USE:
 * Use MutableSharedFlow when you want to send events manually.
 *
 * SharedFlow is read-only.
 * MutableSharedFlow is used to emit values.
 *
 * Common use cases:
 * - Send snackbar messages
 * - Send navigation events
 * - Send toast messages
 * - Send one-time actions
 */

/**
 * Private MutableSharedFlow.
 *
 * This is used inside the owner to send events.
 */
private val _events = MutableSharedFlow<String>()

/**
 * Public read-only SharedFlow.
 *
 * Other parts of the app can collect events,
 * but they cannot emit events directly.
 */
val events: SharedFlow<String> = _events

fun main() = runBlocking {
    println("=== MUTABLE SHAREDFLOW EXAMPLE ===")
    /**
     * Collector.
     *
     * This simulates the UI listening for events.
     */
    val collectorJob = launch {
        events.collect { event ->
            println("Collector received event: $event")
        }
    }
    delay(1000)
    /**
     * emit()
     *
     * emit() is a suspend function.
     * It sends a value to active collectors.
     */
    println("Sending event using emit()")
    _events.emit("show snackbar")
    delay(1000)
    /**
     * tryEmit()
     *
     * tryEmit() is not a suspend function.
     * It tries to send a value immediately.
     *
     * It returns true if the value was accepted.
     * It returns false if the value could not be accepted.
     */
    println("Sending event using tryEmit()")
    val wasSent = _events.tryEmit("Navigate to profile screen")
    println("Was tryEmit successful ? $wasSent")
    delay(1000)
    /**
     * Another normal emit.
     */
    println("Sending another event using emit()")
    _events.emit("Show success toast")
    delay(1000)
    collectorJob.cancel()
    println("===DEMO COMPLETED ===")



}