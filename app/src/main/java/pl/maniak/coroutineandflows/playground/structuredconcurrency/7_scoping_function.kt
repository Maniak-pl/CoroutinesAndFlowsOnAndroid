package pl.maniak.coroutineandflows.playground.structuredconcurrency

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope

fun main() {
    val scope = CoroutineScope(Job())

    scope.launch {

        doSomeTasks()

        launch {
            println("Starting Task 3")
            delay(300)
            println("Task 3 completed")
        }
    }

    Thread.sleep(1000)
}

suspend fun doSomeTasks() = supervisorScope {
    launch {
        println("Starting Task 1")
        delay(100)
        println("Task 1 completed")
    }

    launch {
        println("Starting Task 2")
        delay(200)
        println("Task 2 completed")
    }
}