package pl.maniak.coroutineandflows.playground.fundamentals

fun main() {
    println("Main starts")
    routine(1, 5000)
    routine(2, 3000)
    println("Main ends")
}

fun routine(number: Int, delay: Long) {
    println("Routine $number starts work")
    Thread.sleep(delay)
    println("Routine $number has finished")
}