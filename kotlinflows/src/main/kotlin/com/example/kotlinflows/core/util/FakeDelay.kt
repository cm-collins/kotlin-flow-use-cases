package com.example.kotlinflows.core.util

import kotlinx.coroutines.delay

suspend fun fakeNetworkDelay() {
    delay(Constants.FAKE_NETWORK_DELAY)
}