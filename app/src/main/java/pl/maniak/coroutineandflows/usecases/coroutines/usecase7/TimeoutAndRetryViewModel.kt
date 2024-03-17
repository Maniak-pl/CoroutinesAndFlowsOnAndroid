package pl.maniak.coroutineandflows.usecases.coroutines.usecase7

import pl.maniak.coroutineandflows.base.BaseViewModel
import pl.maniak.coroutineandflows.mock.MockApi

class TimeoutAndRetryViewModel(
    private val api: MockApi = mockApi()
) : BaseViewModel<UiState>() {

    fun performNetworkRequest() {
        uiState.value = UiState.Loading
        val numberOfRetries = 2
        val timeout = 1000L

        // TODO: Exercise 3
        // switch to branch "coroutine_course_full" to see solution

        // run api.getAndroidVersionFeatures(27) and api.getAndroidVersionFeatures(28) in parallel

    }
}