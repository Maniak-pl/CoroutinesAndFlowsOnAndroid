package pl.maniak.coroutineandflows.usecases.coroutines.usecase13

import pl.maniak.coroutineandflows.mock.VersionFeatures

sealed class UiState {
    object Loading : UiState()
    data class Success(
        val versionFeatures: List<VersionFeatures>
    ) : UiState()

    data class Error(val message: String) : UiState()
}