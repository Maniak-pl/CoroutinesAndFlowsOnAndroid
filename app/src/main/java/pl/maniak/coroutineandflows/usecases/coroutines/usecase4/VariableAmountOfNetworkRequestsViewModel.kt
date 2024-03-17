package pl.maniak.coroutineandflows.usecases.coroutines.usecase4

import pl.maniak.coroutineandflows.base.BaseViewModel
import pl.maniak.coroutineandflows.mock.MockApi

class VariableAmountOfNetworkRequestsViewModel(
    private val mockApi: MockApi = mockApi()
) : BaseViewModel<UiState>() {

    fun performNetworkRequestsSequentially() {

    }

    fun performNetworkRequestsConcurrently() {

    }
}