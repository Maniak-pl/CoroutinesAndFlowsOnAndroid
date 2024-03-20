package pl.maniak.coroutineandflows.playground.fundamentals

import android.os.Handler
import android.os.Looper
import kotlinx.coroutines.async
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    println("Main starts")
    joinAll(
        async { delayDemonstration(1, 5000) },
        async { delayDemonstration(2, 3000) }
    )
    println("Main ends")
}

fun delayDemonstration(number: Int, delay: Long) {
    println("Coroutine $number starts work")

    // delay(delay) for Android
    Handler(Looper.getMainLooper())
        .postDelayed({ println("Coroutine $number has finished") }, delay)
}