package com.example.kotlinflows.sharedflow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * SHAREDFLOW REPLAY EXAMPLE
 *
 * CIRCUMSTANCE OF USE:
 * Use replay when you want a SharedFlow to remember some previously emitted values.
 *
 * By default, SharedFlow does not keep old values.
 *
 * That means:
 * - If a collector is active, it receives emitted values.
 * - If a collector starts late, it misses values that were already emitted.
 *
 * replay changes that behavior.
 *
 * replay = 1 means:
 * - Remember the latest 1 emitted value.
 * - A new collector receives that latest value immediately.
 */

/**
 * MutableSharedFlow with replay = 1.
 *
 * This means the SharedFlow will remember the latest emitted value.
 */

private  val _newsUpdates = MutableSharedFlow<String>(
    replay = 1
)

/**
 * Public read-only SharedFlow.
 */

val newsUpdates: SharedFlow<String> = _newsUpdates

fun main () = runBlocking {
    println("=== SHAREDFLOW REPLAY EXAMPLE ===")
    /**
     * Emit a value before any collector starts.
     *
     * Because replay = 1, this value will be remembered.
     */
    println("Sending first update before collector starts")
    _newsUpdates.emit("Breaking news: Kotlin Flow is powerful")
    delay(1000)
    /**
     * Collector 1 starts late.
     *
     * Because replay = 1, it immediately receives the latest emitted value.
     */
    println("\nCollector 1 starts collecting")
    val collectorOne = launch {
        newsUpdates.collect { update ->
            println("Collector 1 received: $update")
        }
    }

    delay(1000)
    /**
     * Emit another value.
     *
     * Collector 1 is active, so it receives this value.
     * This value also becomes the latest replayed value.
     */
    println("\nSending second update")
    _newsUpdates.emit("Update: SharedFlow can replay latest values")

    delay(1000)
    /**
     * Collector 2 starts even later.
     *
     * It does not receive all old values.
     * It receives only the latest replayed value.
     */
    println("\nCollector 2 starts collecting")
    val collectorTwo = launch {
        newsUpdates.collect { update ->
            println("Collector 2 received: $update")
        }
    }

    delay(1000)
    /**
     * Emit final value.
     *
     * Both active collectors receive it.
     */
    println("\nSending final update")
    _newsUpdates.emit("Final Update: replay is useful for late collectors")

    delay(1000)
    /**
     * Cancel collectors because this is a standalone demo.
     */

    collectorOne.cancel()
    collectorTwo.cancel()

    println("\n ===DEMO COMPLETED ===")
}
