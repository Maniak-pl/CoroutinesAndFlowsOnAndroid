package pl.maniak.coroutineandflows.usecases.coroutines.usecase2.rx

import pl.maniak.coroutineandflows.mock.VersionFeatures

sealed class UiState {
    object Loading : UiState()
    data class Success(
        val versionFeatures: VersionFeatures
    ) : UiState()

    data class Error(val message: String) : UiState()
}