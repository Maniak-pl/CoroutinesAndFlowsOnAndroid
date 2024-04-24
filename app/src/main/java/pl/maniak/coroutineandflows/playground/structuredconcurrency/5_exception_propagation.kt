package pl.maniak.coroutineandflows.playground.structuredconcurrency

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking<Unit> {

    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println("Caught $throwable in CoroutineExceptionHandler")
    }

    // val scope = CoroutineScope(Job() + exceptionHandler)
    val scope = CoroutineScope(SupervisorJob() + exceptionHandler)
    scope.launch {
        println("Coroutine 1 starts")
        delay(50)
        println("Coroutine 1 failed")
        throw RuntimeException("Coroutine 1 failed")
    }

    scope.launch {
        println("Coroutine 2 starts")
        delay(500)
        println("Coroutine 2 completed")
    }.invokeOnCompletion { throwable ->
        if (throwable is CancellationException) {
            println("Coroutine 2 got cancelled")
        }
    }

    Thread.sleep(1000)
    println("Scope got cancelled: ${!scope.isActive}")
}