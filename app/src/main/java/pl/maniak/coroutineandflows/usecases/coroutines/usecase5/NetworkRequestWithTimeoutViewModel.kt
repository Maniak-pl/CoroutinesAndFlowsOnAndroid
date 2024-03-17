package pl.maniak.coroutineandflows.usecases.coroutines.usecase5

import pl.maniak.coroutineandflows.base.BaseViewModel
import pl.maniak.coroutineandflows.mock.MockApi

class NetworkRequestWithTimeoutViewModel(
    private val api: MockApi = mockApi()
) : BaseViewModel<UiState>() {

    fun performNetworkRequest(timeout: Long) {

    }

}