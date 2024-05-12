package pl.maniak.coroutineandflows.playground.exceptionhandling

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun main() {
    tryCatch()
    exceptionHandler()
}

private fun tryCatch() {
    val scope = CoroutineScope(Job())

    scope.launch {

        launch {
            println("Start coroutine 1")
            delay(100)
            try {
                throw RuntimeException()
            } catch (exception: RuntimeException) {
                println("Coroutine 1 caught exception: $exception")

            }
        }

        launch {
            println("Start coroutine 2")
            delay(3000)
            println("Coroutine 2 completed")
        }
    }

    Thread.sleep(5000)
}

private fun exceptionHandler() {
    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println("Caught exception:  $throwable")
    }
    val scope = CoroutineScope(Job())

    scope.launch(exceptionHandler) {

        launch {
            println("Start coroutine 1")
            delay(100)
            throw RuntimeException()

        }

        launch {
            println("Start coroutine 2")
            delay(3000)
            println("Coroutine 2 completed")
        }
    }

    Thread.sleep(5000)
}
