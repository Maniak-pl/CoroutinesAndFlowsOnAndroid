package pl.maniak.coroutineandflows.playground.coroutinebuilders

import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    firstExample()
    secondExample()
    thirdExample()
}

fun firstExample() {
    GlobalScope.launch {
        delay(500)
        println("FirstExample - printed from within Coroutine")
    }
    Thread.sleep(1000)
    println("FirstExample - Main ends")
}

fun secondExample() {
    runBlocking {
        launch {
            delay(500)
            println("SecondExample - printed from within Coroutine")
        }
    }
}

fun thirdExample() {
    runBlocking {
        val job = launch(start = CoroutineStart.LAZY) {
            networkRequest()
            println("ThirdExample - result received")
        }
        delay(200)
        job.start()
        println("ThirdExample - end of runBlocking")
    }
}

suspend fun networkRequest(): String {
    delay(500)
    return "NetworkRequest() - Result"
}