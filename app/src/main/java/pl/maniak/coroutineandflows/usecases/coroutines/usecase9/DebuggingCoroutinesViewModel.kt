package pl.maniak.coroutineandflows.usecases.coroutines.usecase9

import pl.maniak.coroutineandflows.base.BaseViewModel
import pl.maniak.coroutineandflows.mock.MockApi

class DebuggingCoroutinesViewModel(
    private val api: MockApi = mockApi()
) : BaseViewModel<UiState>() {

    fun performSingleNetworkRequest() {

    }
}