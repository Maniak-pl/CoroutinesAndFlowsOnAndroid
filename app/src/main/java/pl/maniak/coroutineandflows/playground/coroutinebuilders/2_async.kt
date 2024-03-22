package pl.maniak.coroutineandflows.playground.coroutinebuilders

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    jobJoin()
    deferredAwait()
}

fun jobJoin() {
    runBlocking {
        val startTime = System.currentTimeMillis()

        val resultList = mutableListOf<String>()

        val job1 = launch {
            val result1 = networkCall(1)
            resultList.add(result1)
            println("result received: $result1 after ${elapsedMillis(startTime)}ms")
        }
        val job2 = launch {
            val result2 = networkCall(2)
            resultList.add(result2)
            println("result received: $result2 after ${elapsedMillis(startTime)}ms")
        }
        job1.join()
        job2.join()

        println("All results received: $resultList after ${elapsedMillis(startTime)}ms")
    }
}

fun deferredAwait() {
    runBlocking {
        val startTime = System.currentTimeMillis()

        val deferred1 = async {
            val result1 = networkCall(1)
            println("result received: $result1 after ${elapsedMillis(startTime)}ms")
            result1
        }
        val deferred2 = async {
            val result2 = networkCall(2)
            println("result received: $result2 after ${elapsedMillis(startTime)}ms")
            result2
        }

        val resultList = listOf(deferred1.await(), deferred2.await())

        println("All results received: $resultList after ${elapsedMillis(startTime)}ms")
    }
}

suspend fun networkCall(number: Int): String {
    delay(500)
    return "Result $number"
}

fun elapsedMillis(startTime: Long) = System.currentTimeMillis() - startTime