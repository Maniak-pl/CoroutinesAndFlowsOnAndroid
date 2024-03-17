package pl.maniak.coroutineandflows.usecases.coroutines.usecase2.rx

import pl.maniak.coroutineandflows.base.BaseViewModel

class SequentialNetworkRequestsRxViewModel(
    private val mockApi: RxMockApi = mockApi()
) : BaseViewModel<UiState>() {

    fun perform2SequentialNetworkRequest() {

    }
}