package com.example.kotlinflows.core.result

sealed interface AppResult<out T> {
    data object Loading : AppResult<Nothing>

    data class Success<T>
        (
        val data: T
    ) : AppResult<T>

    data class  Error(
        val message: String
    ): AppResult<Nothing>
}