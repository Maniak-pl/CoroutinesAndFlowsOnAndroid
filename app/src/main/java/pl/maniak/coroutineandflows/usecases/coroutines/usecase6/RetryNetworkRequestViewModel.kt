package pl.maniak.coroutineandflows.usecases.coroutines.usecase6

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pl.maniak.coroutineandflows.base.BaseViewModel
import pl.maniak.coroutineandflows.mock.MockApi
import timber.log.Timber

class RetryNetworkRequestViewModel(
    private val api: MockApi = mockApi()
) : BaseViewModel<UiState>() {

    fun performNetworkRequest() {
        uiState.value = UiState.Loading

        viewModelScope.launch {
            val numberOfRetries = 2

            try {
                retry(numberOfRetries) {
                    loadRecentAndroidVersions()
                }
            } catch (exception: Exception) {
                uiState.value = UiState.Error("Network request failed")
            }
        }
    }

    private suspend fun <T> retry(numberOfRetries: Int, block: suspend () -> T): T {
        repeat(numberOfRetries) {
            try {
                return block()
            } catch (exception: Exception) {
                Timber.e(exception)
            }
        }
        return block()
    }

    private suspend fun loadRecentAndroidVersions() {
        val result = api.getRecentAndroidVersions()
        uiState.value = UiState.Success(result)
    }
}