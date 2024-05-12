package pl.maniak.coroutineandflows.playground.exceptionhandling

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun main() {
    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println("Caught exception:  $throwable")
    }
    val scope = CoroutineScope(Job() + exceptionHandler)

    scope.launch {
        async {
            delay(100)
            try {
                throw RuntimeException()
            } catch (exception: RuntimeException) {
                println("Coroutine 1 caught exception: $exception")
            }
        }
    }

    val deferred = scope.async {
        delay(200)
        throw RuntimeException()
    }

    scope.launch {
        deferred.await()
    }

    Thread.sleep(1000)
}