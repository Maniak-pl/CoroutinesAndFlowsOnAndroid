package pl.maniak.coroutineandflows.playground.fundamentals

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

fun main() = runBlocking {
    println("Main starts")
    joinAll(
        async { threadSwitchingCoroutine(1, 5000) },
        async { threadSwitchingCoroutine(2, 3000) }
    )
    println("Main ends")
}

suspend fun threadSwitchingCoroutine(number: Int, delay: Long) {
    println("Coroutine $number starts work on ${Thread.currentThread().name}")
    delay(delay)
    withContext(Dispatchers.Default) {
        println("Coroutine $number is still working on ${Thread.currentThread().name}")
    }
}