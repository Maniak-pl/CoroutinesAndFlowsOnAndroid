package pl.maniak.coroutineandflows.usecases.coroutines.usecase5

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout
import kotlinx.coroutines.withTimeoutOrNull
import pl.maniak.coroutineandflows.base.BaseViewModel
import pl.maniak.coroutineandflows.mock.MockApi
import timber.log.Timber

class NetworkRequestWithTimeoutViewModel(
    private val api: MockApi = mockApi()
) : BaseViewModel<UiState>() {

    fun performNetworkRequest(timeout: Long) {
        uiState.value = UiState.Loading

        usingWithTimeOut(timeout)
        usingWithTimeOutOrNull(timeout)
    }

    private fun usingWithTimeOut(timeout: Long) {
        viewModelScope.launch {
            try {
                val result = withTimeout(timeout) {
                    api.getRecentAndroidVersions()
                }
                uiState.value = UiState.Success(result)
            } catch (timeoutCancellationException: TimeoutCancellationException) {
                Timber.e(timeoutCancellationException)
                uiState.value = UiState.Error("Network request timed out")
            } catch (exception: Exception) {
                Timber.e(exception)
                uiState.value = UiState.Error("Network request failed")
            }
        }
    }

    private fun usingWithTimeOutOrNull(timeout: Long) {
        viewModelScope.launch {
            try {
                val result = withTimeoutOrNull(timeout) {
                    api.getRecentAndroidVersions()
                }
                if (result != null) {
                    uiState.value = UiState.Success(result)
                } else {
                    uiState.value = UiState.Error("Network request timed out or null result")
                }
            } catch (exception: Exception) {
                Timber.e(exception)
                uiState.value = UiState.Error("Network request failed")
            }
        }
    }
}