package pl.maniak.coroutineandflows.playground.structuredconcurrency

import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@Suppress("OPT_IN_IS_NOT_ENABLED")
@OptIn(DelicateCoroutinesApi::class)
fun main() {

    println("Job of GlobalScope: ${GlobalScope.coroutineContext[Job]}")

    GlobalScope.launch {
        println(
            "If we write global scope launch. The coroutine that he started wouldn't have any connection to the scope. " +
                    "This is obvious because the only way of forming relations between the coroutine and the scope would be via their job objects. " +
                    "And as the global scope doesn't even have a job object, there is no way of building some kind of parent child relation."
        )
    }

    Thread.sleep(1000)
}