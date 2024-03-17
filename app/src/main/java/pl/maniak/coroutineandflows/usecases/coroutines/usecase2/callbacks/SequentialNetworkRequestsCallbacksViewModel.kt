package pl.maniak.coroutineandflows.usecases.coroutines.usecase2.callbacks

import pl.maniak.coroutineandflows.base.BaseViewModel

class SequentialNetworkRequestsCallbacksViewModel(
    private val mockApi: CallbackMockApi = mockApi()
) : BaseViewModel<UiState>() {

    fun perform2SequentialNetworkRequest() {

    }
}