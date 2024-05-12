package pl.maniak.coroutineandflows.playground.exceptionhandling

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking<Unit> {

    try {
        coroutineScope {
            launch {
                throw RuntimeException()
            }
        }
    } catch (e: Exception) {
        println("Caught 1 exception: $e")
    }

    try {
        doSomethingSuspend()
    } catch (e: Exception) {
        println("Caught 2 exception: $e")
    }
}

private suspend fun doSomethingSuspend() {
    coroutineScope {
        launch {
            throw RuntimeException()
        }
    }
}