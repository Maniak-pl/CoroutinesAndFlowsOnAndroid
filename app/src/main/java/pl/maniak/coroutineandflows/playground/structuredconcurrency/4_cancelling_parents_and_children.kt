package pl.maniak.coroutineandflows.playground.structuredconcurrency

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.cancellation.CancellationException

fun main() = runBlocking {
    cancellingParentJob()
    cancellingChildrenJob()
}

suspend fun cancellingParentJob() {
    println("cancellingParentJob()")
    val scope = CoroutineScope(Dispatchers.Default)

    scope.launch {
        delay(1000)
        println("Coroutine 1 completed")
    }.invokeOnCompletion { throwable ->
        if (throwable is CancellationException) {
            println("Coroutine 1 was cancelled!")
        }
    }

    scope.launch {
        delay(1000)
        println("Coroutine 2 completed")
    }.invokeOnCompletion { throwable ->
        if (throwable is CancellationException) {
            println("Coroutine 2 was cancelled!")
        }
    }

    scope.cancel()
    scope.coroutineContext[Job]!!.join()
}

suspend fun cancellingChildrenJob() {
    println("cancellingChildrenJob()")
    val scope = CoroutineScope(Dispatchers.Default)

    scope.coroutineContext[Job]!!.invokeOnCompletion { throwable ->
        if (throwable is CancellationException) {
            println("Parent coroutine was cancelled!")
        }
    }

    val childCoroutine1Job = scope.launch {
        delay(1000)
        println("Coroutine 1 completed")
    }

    childCoroutine1Job.invokeOnCompletion { throwable ->
        if (throwable is CancellationException) {
            println("Coroutine 1 was cancelled!")
        }
    }

    scope.launch {
        delay(1000)
        println("Coroutine 2 completed")
    }.invokeOnCompletion { throwable ->
        if (throwable is CancellationException) {
            println("Coroutine 1 was cancelled!")
        }
    }

    delay(200)
    childCoroutine1Job.cancelAndJoin()
}