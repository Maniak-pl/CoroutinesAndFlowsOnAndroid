package pl.maniak.coroutineandflows.usecases.coroutines.usecase1

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pl.maniak.coroutineandflows.base.BaseViewModel
import pl.maniak.coroutineandflows.mock.MockApi
import timber.log.Timber
import kotlin.coroutines.cancellation.CancellationException

class PerformSingleNetworkRequestViewModel(
    private val mockApi: MockApi = mockApi()
) : BaseViewModel<UiState>() {

    fun performSingleNetworkRequest() {
        uiState.value = UiState.Loading

        val job = viewModelScope.launch(Dispatchers.Main) {
            Timber.d("I am the first statement in the coroutine with Dispatchers.Main.")
            try {
                val recentAndroidVersion = mockApi.getRecentAndroidVersions()
                uiState.value = UiState.Success(recentAndroidVersion)
            } catch (exception: Exception) {
                Timber.e(exception)
                uiState.value = UiState.Error("Network request failed")
            }
        }

        Timber.d("I am the first statement after launching the coroutine.")

        job.invokeOnCompletion { throwable ->
            if (throwable is CancellationException) {
                Timber.d("Coroutine was cancelled!")
            }
        }
    }
}