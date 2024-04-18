package pl.maniak.coroutineandflows.playground.structuredconcurrency

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


val scope = CoroutineScope(Dispatchers.Default)
fun main() = runBlocking {
    val job = scope. launch {
        delay(100)
        println("Coroutine completed")
    }

    job.invokeOnCompletion { throwable ->
        if(throwable is CancellationException) {
            println("Coroutine was cancelled!")
        }
    }

    delay(50)
    onDestroy()
}

fun onDestroy() {
    println("Life-time of scope ends")
    scope.cancel()
}