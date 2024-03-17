package pl.maniak.coroutineandflows.usecases.coroutines.usecase9

import pl.maniak.coroutineandflows.mock.AndroidVersion

sealed class UiState {
    object Loading : UiState()
    data class Success(val recentVersions: List<AndroidVersion>) : UiState()
    data class Error(val message: String) : UiState()
}