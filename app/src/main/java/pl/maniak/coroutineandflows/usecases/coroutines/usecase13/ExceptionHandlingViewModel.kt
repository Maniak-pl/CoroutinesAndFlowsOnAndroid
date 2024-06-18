package pl.maniak.coroutineandflows.usecases.coroutines.usecase13

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pl.maniak.coroutineandflows.base.BaseViewModel
import pl.maniak.coroutineandflows.mock.MockApi
import retrofit2.HttpException

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

    }

    fun showResultsEvenIfChildCoroutineFails() {

    }
}