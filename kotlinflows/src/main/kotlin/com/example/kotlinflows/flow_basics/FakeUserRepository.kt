package com.example.kotlinflows.flow_basics

import com.example.kotlinflows.core.model.User
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking

/**
 * FAKE REPOSITORY EXAMPLE
 * 
 * CIRCUMSTANCE OF USE:
 * In a real Android app, the Repository pattern is used to abstract 
 * the data source (API, Database, etc.). Returning a Flow from a 
 * repository allows the UI to automatically react to data changes.
 */

// This function simulates fetching a list of users from a remote source.
fun getUsers(): Flow<List<User>> = flow {
    // 1. Emit an empty list immediately to show a "Loading" or "Initial" state
    println("[Repository] Emitting initial state (empty list)...")
    emit(emptyList())

    // 2. Simulate network or database latency
    delay(2000)

    // 3. Emit the actual data
    println("[Repository] Data fetched successfully! Emitting user list...")
    val users = listOf(
        User(
            id = 1,
            name = "Collins Munene",
            email = "collins@example.com",
            isActive = true
        ),
        User(
            id = 2,
            name = "Jane Doe",
            email = "jane@example.com",
            isActive = false
        ),
        User(
            id = 3,
            name = "John Smith",
            email = "john@example.com",
            isActive = true
        )
    )
    emit(users)
}

fun main() = runBlocking {
    println("=== REPOSITORY FLOW DEMO ===")
    
    /**
     * When we collect this Flow, we see two distinct events:
     * 1. Immediate receipt of an empty list.
     * 2. Receipt of the full list after a delay.
     */
    getUsers().collect { userList ->
        if (userList.isEmpty()) {
            println("UI Status: Loading users...")
        } else {
            println("UI Status: Displaying ${userList.size} users:")
            userList.forEach { user ->
                val status = if (user.isActive) "Active" else "Inactive"
                println(" - ${user.name} (${user.email}) | Status: $status")
            }
        }
    }

    println("\n=== DEMO COMPLETED ===")
}
