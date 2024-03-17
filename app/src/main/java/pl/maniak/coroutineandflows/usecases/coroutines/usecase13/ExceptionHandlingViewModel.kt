package pl.maniak.coroutineandflows.usecases.coroutines.usecase13

import pl.maniak.coroutineandflows.base.BaseViewModel
import pl.maniak.coroutineandflows.mock.MockApi

class ExceptionHandlingViewModel(
    private val api: MockApi = mockApi()
) : BaseViewModel<UiState>() {

    fun handleExceptionWithTryCatch() {

    }

    fun handleWithCoroutineExceptionHandler() {

    }

    fun showResultsEvenIfChildCoroutineFails() {

    }
}