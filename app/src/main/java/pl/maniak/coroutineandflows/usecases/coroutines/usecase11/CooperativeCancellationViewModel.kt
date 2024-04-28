package pl.maniak.coroutineandflows.usecases.coroutines.usecase11

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.coroutines.yield
import timber.log.Timber
import java.math.BigInteger
import kotlin.coroutines.cancellation.CancellationException
import kotlin.system.measureTimeMillis

class CooperativeCancellationViewModel(
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default
) : ViewModel() {

    private var calculationJob: Job? = null
    fun performCalculation(factorialOf: Int) {
        Timber.e("performCalculation(factorialOf=$factorialOf)")

        uiState.value = UiState.Loading

        calculationJob = viewModelScope.launch {
            Timber.e("Coroutine Context: $coroutineContext")
            var result: BigInteger
            val computationDuration = measureTimeMillis {
                result = calculateFactorialOf(factorialOf)
            }

            var resultString: String
            val stringConversionDuration = measureTimeMillis {
                resultString = withContext(Dispatchers.Default) {
                    result.toString()
                }
            }

            uiState.value =
                UiState.Success(resultString, computationDuration, stringConversionDuration)
        }

        calculationJob?.invokeOnCompletion { throwable ->
            if (throwable is CancellationException) {
                Timber.e("Calculation was cancelled")
                uiState.value = UiState.Error("Calculation was cancelled")
            }
        }
    }

    private suspend fun calculateFactorialOf(number: Int) = withContext(defaultDispatcher) {
        var factorial = BigInteger.ONE
        for (i in 1..number) {
            yield()
            factorial = factorial.multiply(BigInteger.valueOf(i.toLong()))
        }
        Timber.e("Calculating Factorial Completed")
        factorial
    }

    fun cancelCalculation() {
        calculationJob?.cancel()
    }

    fun uiState(): LiveData<UiState> = uiState

    private val uiState: MutableLiveData<UiState> = MutableLiveData()
}