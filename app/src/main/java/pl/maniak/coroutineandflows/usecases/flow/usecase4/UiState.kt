package pl.maniak.coroutineandflows.usecases.flow.usecase4

import pl.maniak.coroutineandflows.usecases.flow.mock.Stock

sealed class UiState {
    object Loading : UiState()
    data class Success(val stockList: List<Stock>) : UiState()
    data class Error(val message: String) : UiState()
}