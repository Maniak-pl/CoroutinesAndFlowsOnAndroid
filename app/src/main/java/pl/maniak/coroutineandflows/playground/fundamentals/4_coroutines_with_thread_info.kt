package pl.maniak.coroutineandflows.playground.fundamentals

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    println("Main starts")
    joinAll(
        async { coroutineWithThreadInfo(1, 5000) },
        async { coroutineWithThreadInfo(2, 3000) }
    )
    println("Main ends")
}

suspend fun coroutineWithThreadInfo(number: Int, delay: Long) {
    println("Coroutine $number starts work on ${Thread.currentThread().name}")
    delay(delay)
    println("Coroutine $number has finished on ${Thread.currentThread().name}")
}