package pl.maniak.coroutineandflows.usecases.coroutines.usecase12

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import pl.maniak.coroutineandflows.base.BaseViewModel
import java.math.BigInteger
import kotlin.system.measureTimeMillis

class CalculationInSeveralCoroutinesViewModel(
    private val factorialCalculator: FactorialCalculator = FactorialCalculator(),
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default
) : BaseViewModel<UiState>() {

    fun performCalculation(
        factorialOf: Int,
        numberOfCoroutines: Int
    ) {
        uiState.value = UiState.Loading

        viewModelScope.launch {

            var factorialResult = BigInteger.ZERO
            val computationDuration = measureTimeMillis {
                factorialResult =
                    factorialCalculator.calculateFactorial(
                        factorialOf,
                        numberOfCoroutines
                    )
            }

            var resultString = ""
            val stringConversionDuration = measureTimeMillis {
                resultString = convertToString(factorialResult)
            }

            uiState.value =
                UiState.Success(resultString, computationDuration, stringConversionDuration)
        }
    }

    private suspend fun convertToString(number: BigInteger): String =
        withContext(defaultDispatcher) { number.toString() }
}