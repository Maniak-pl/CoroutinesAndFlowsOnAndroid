package pl.maniak.coroutineandflows.playground.exceptionhandling

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope

fun main() {
    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println("Caught $throwable in CoroutineExceptionHandler")
    }
    val scope = CoroutineScope(Job() + exceptionHandler)

    scope.launch {
        try {
            supervisorScope {
                launch {
                    println("CEH ${coroutineContext[CoroutineExceptionHandler]}")
                    throw RuntimeException()
                }
            }
        } catch (e: Exception) {
            println("Caught exception: $e")
        }
    }

    Thread.sleep(100)
}