package pl.maniak.coroutineandflows.usecases.coroutines.usecase13

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope
import pl.maniak.coroutineandflows.base.BaseViewModel
import pl.maniak.coroutineandflows.mock.MockApi
import retrofit2.HttpException
import timber.log.Timber

class ExceptionHandlingViewModel(
    private val api: MockApi = mockApi()
) : BaseViewModel<UiState>() {

    fun handleExceptionWithTryCatch() {
        uiState.value = UiState.Loading

        viewModelScope.launch {
            try {
                api.getAndroidVersionFeatures(27)
            } catch (exception: Exception) {
                if (exception is HttpException) {
                    if (exception.code() == 500) {
                        uiState.value = UiState.Error("Internal Server Error")
                    } else {
                        uiState.value = UiState.Error("Network Request failed: $exception")
                    }
                } else {
                    uiState.value = UiState.Error("Exception: $exception")
                }
            }
        }
    }

    fun handleWithCoroutineExceptionHandler() {
        uiState.value = UiState.Loading

        val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
            uiState.value = UiState.Error("Network Request failed!")
        }

        viewModelScope.launch(exceptionHandler) {
            api.getAndroidVersionFeatures(27)
        }
    }

    fun showResultsEvenIfChildCoroutineFails() {
        uiState.value = UiState.Loading

        viewModelScope.launch {

            supervisorScope {
                val oreoFeaturesDeferred = async { api.getAndroidVersionFeatures(27) }
                val pieFeaturesDeferred = async { api.getAndroidVersionFeatures(28) }
                val android10FeaturesDeferred = async { api.getAndroidVersionFeatures(29) }

                val versionFeatures =
                    listOf(
                        oreoFeaturesDeferred,
                        pieFeaturesDeferred,
                        android10FeaturesDeferred
                    ).mapNotNull {
                        try {
                            it.await()
                        } catch (exception: Exception) {
                            if (exception is CancellationException) {
                                throw exception
                            }
                            Timber.e("Error loading feature data!")
                            null
                        }
                    }

                uiState.value = UiState.Success(versionFeatures)
            }
        }
    }
}