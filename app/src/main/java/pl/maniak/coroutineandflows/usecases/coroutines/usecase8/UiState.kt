package pl.maniak.coroutineandflows.usecases.coroutines.usecase8

import pl.maniak.coroutineandflows.mock.AndroidVersion

sealed class UiState {
    sealed class Loading : UiState() {
        object LoadFromDb : Loading()
        object LoadFromNetwork : Loading()
    }

    data class Success(val dataSource: DataSource, val recentVersions: List<AndroidVersion>) :
        UiState()

    data class Error(val dataSource: DataSource, val message: String) : UiState()
}