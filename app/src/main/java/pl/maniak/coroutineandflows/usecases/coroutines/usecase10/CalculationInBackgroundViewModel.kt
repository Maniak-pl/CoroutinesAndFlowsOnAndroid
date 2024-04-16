package pl.maniak.coroutineandflows.usecases.coroutines.usecase10

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import pl.maniak.coroutineandflows.base.BaseViewModel
import java.math.BigInteger
import kotlin.system.measureTimeMillis

class CalculationInBackgroundViewModel : BaseViewModel<UiState>() {

    fun performCalculation(factorialOf: Int) {
        uiState.value = UiState.Loading

        viewModelScope.launch {
            var result: BigInteger
            val computationDuration = measureTimeMillis {
                result = calculateFactorialOf(factorialOf)
            }

            var resultString: String
            val stringConversionDuration = measureTimeMillis {
                resultString =
                    withContext(Dispatchers.Default + CoroutineName("String Conversion Coroutine")) {
                        result.toString()
                    }
            }

            uiState.value =
                UiState.Success(resultString, computationDuration, stringConversionDuration)
        }
    }

    private suspend fun calculateFactorialOf(number: Int) = withContext(Dispatchers.Default) {
        var factorial = BigInteger.ONE
        for (i in 1..number) {
            factorial = factorial.multiply(BigInteger.valueOf(i.toLong()))
        }
        factorial
    }
}