package pl.maniak.coroutineandflows.playground.fundamentals

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    println("Main starts")
    joinAll(
        async { coroutine(1, 5000) },
        async { coroutine(2, 3000) }
    )
    println("Main ends")
}

suspend fun coroutine(number: Int, delay: Long) {
    println("Coroutine $number starts work")
    delay(delay)
    println("Coroutine $number has finished")
}