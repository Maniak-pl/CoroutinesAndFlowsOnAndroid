package pl.maniak.coroutineandflows.playground.exceptionhandling

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

fun main() {
    try {
        functionThatThrows()
    } catch (exception: RuntimeException) {
        println("Main - Caught exception: $exception")
    }

    val scope = CoroutineScope(Job())

    scope.launch {
        // functionThatThrows() // Exception in thread "DefaultDispatcher-worker-1" java.lang.RuntimeException: Some exception
        try {
            launch {
                // functionThatThrows() // Exception in thread "DefaultDispatcher-worker-1" java.lang.RuntimeException: Some exception
                try {
                    functionThatThrows()
                } catch (exception: RuntimeException) {
                    println("Coroutine 2 -Caught exception: $exception")
                }
            }
            functionThatThrows()
        } catch (exception: RuntimeException) {
            println("Coroutine 1 - Caught exception: $exception")
        }
    }

    Thread.sleep(1000)
}

private fun functionThatThrows() {
    throw RuntimeException("Some exception")
}
