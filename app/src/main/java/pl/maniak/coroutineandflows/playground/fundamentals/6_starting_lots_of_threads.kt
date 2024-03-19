package pl.maniak.coroutineandflows.playground.fundamentals

import kotlin.concurrent.thread

fun main() {
    repeat(1_000_000) {
        thread {
            Thread.sleep(5000)
            print(".")
        }
    }
    // Exception in thread "main" java.lang.OutOfMemoryError: unable to create new native thread
}