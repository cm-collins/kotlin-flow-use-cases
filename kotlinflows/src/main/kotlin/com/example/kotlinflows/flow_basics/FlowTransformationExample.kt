package com.example.kotlinflows.flow_basics

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

/**
 * This file shows simple Flow transformations.
 *
 * map() changes each value.
 * filter() keeps only values that match a condition.
 */

fun main() = runBlocking {
    fun originalNumbers(): Flow<Int>{
        return flow {
            emit(10)
            emit(20)
            emit(30)
            emit(40)
            emit(50)
        }

    }


    fun doubleNumbers(): Flow<Int> {
        return originalNumbers()
            .map { number ->
                //each number is multiplied by 2
                number * 2


            }
    }
    println("Doubled numbers are ${doubleNumbers()}")


}