package pl.maniak.coroutineandflows.playground.cancellation

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.cancellation.CancellationException

fun main() = runBlocking {

    val job = launch(Dispatchers.Default) {

        repeat(10) { index ->
            // ensureActive()
            // yield()
            if (isActive) {
                println("operation number $index")
                Thread.sleep(100)
            } else {
                println("Cleaning up ...")
                throw CancellationException()
            }
        }
    }

    delay(250)
    println("Cancelling Coroutine")
    job.cancel()
}