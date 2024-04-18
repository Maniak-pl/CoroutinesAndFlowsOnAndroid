package pl.maniak.coroutineandflows.playground.structuredconcurrency

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun main() {
    childCoroutineJob()
    passedJob()
}

fun childCoroutineJob() {
    println("== Child coroutine job ==")
    val scopeJob = Job()
    val scope = CoroutineScope(Dispatchers.Default + scopeJob)

    var childCoroutineJob: Job? = null

    val coroutineJob = scope.launch {
        childCoroutineJob = launch {
            println("Starting child coroutine")
            delay(1000)
        }
        println("Starting coroutine")
        delay(1000)
    }

    Thread.sleep(1000)
    println(
        "Is childCoroutineJob a child of coroutineJob? ${
            coroutineJob.children.contains(childCoroutineJob)
        }"
    )

    println("Is coroutineJob a child of scopeJob? ${scopeJob.children.contains(coroutineJob)}")
}

fun passedJob() {
    println("== Passed job ==")
    val scopeJob = Job()
    val scope = CoroutineScope(Dispatchers.Default + scopeJob)
    val passedJob = Job()


    val coroutineJob = scope.launch(passedJob) {
        println("Starting coroutine")
        delay(1000)
    }

    Thread.sleep(1000)
    println("passedJob and coroutineJob are the same: ${coroutineJob == passedJob}")

    println("Is coroutineJob a child of scopeJob? ${scopeJob.children.contains(coroutineJob)}")
}