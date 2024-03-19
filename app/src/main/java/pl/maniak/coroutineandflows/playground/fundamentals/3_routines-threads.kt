package pl.maniak.coroutineandflows.playground.fundamentals

import kotlin.concurrent.thread

fun main() {
    println("Main starts")
    threadRoutine(1, 500)
    threadRoutine(2, 300)
    Thread.sleep(1000)
    println("Main ends")
}

fun threadRoutine(number: Int, delay: Long) {
    thread {
        println("Routine $number starts work on ${Thread.currentThread().name}")
        Thread.sleep(delay)
        println("Routine $number has finished on ${Thread.currentThread().name}")
    }

}