package com.example.kotlinflows.sharedflow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * BASIC SHAREDFLOW EXAMPLE
 *
 * CIRCUMSTANCE OF USE:
 * Use SharedFlow when you want to send values or events to one or more collectors.
 *
 * SharedFlow is commonly used for:
 * - Snackbar messages
 * - Toast messages
 * - Navigation events
 * - One-time UI actions
 * - Broadcasting values to many collectors
 *
 * Main idea:
 *
 * StateFlow = stores the latest state
 * SharedFlow = sends events to collectors
 */

/**
 * MutableSharedFlow is used when we want to emit values.
 *
 * Unlike StateFlow, SharedFlow does not require an initial value.
 */
private val _messages = MutableSharedFlow<String>()

/**
 * SharedFlow is the read-only version.
 *
 * Other parts of the app can collect messages,
 * but they cannot emit new messages directly.
 */
val messages: SharedFlow<String> = _messages

fun main() = runBlocking {
    println("=== BASIC SHAREDFLOW EXAMPLE ===")
    /**
     * Collector.
     *
     * This collector listens for messages from the SharedFlow.
     *
     * Important:
     * SharedFlow does not automatically emit an initial value.
     * It only emits when we call emit().
     */
    val collectorJob = launch {
        messages.collect { messages ->
            println("Collector Received messages: $messages")
        }
    }

    delay(1000)
    /**
     * Emit the first message.
     *
     * emit() is a suspend function.
     * It sends a value to the active collectors.
     */

    println("sending first message")
    _messages.emit(
        "Hello from SharedFlow"
    )

    delay(1000)
    println("Sending second message  ")
    _messages.emit("Sharedflow is useful for events")
    delay(1000)
    println("Sending third message")
    _messages.emit("This message is received by the active collector")
    delay(1000)
    /**
     * SharedFlow keeps collecting because it is designed for ongoing events.
     *
     * We cancel the collector manually because this is a standalone demo.
     */
    collectorJob.cancel()
    println("=== DEMO COMPLETED ===")


}