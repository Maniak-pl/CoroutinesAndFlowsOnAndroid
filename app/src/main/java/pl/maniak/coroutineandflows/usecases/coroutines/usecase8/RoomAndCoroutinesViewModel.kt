package pl.maniak.coroutineandflows.usecases.coroutines.usecase8

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pl.maniak.coroutineandflows.base.BaseViewModel
import pl.maniak.coroutineandflows.mock.MockApi
import pl.maniak.coroutineandflows.usecases.coroutines.usecase8.DataSource.DATABASE
import pl.maniak.coroutineandflows.usecases.coroutines.usecase8.DataSource.NETWORK

class RoomAndCoroutinesViewModel(
    private val api: MockApi,
    private val database: AndroidVersionDao
) : BaseViewModel<UiState>() {

    fun loadData() {
        uiState.value = UiState.Loading.LoadFromDb

        viewModelScope.launch {
            val localVersions = database.getAndroidVersions()
            if (localVersions.isEmpty()) {
                uiState.value = UiState.Error(DATABASE, "Database is empty")
            } else {
                uiState.value = UiState.Success(DATABASE, localVersions.mapToUiModelList())
            }

            uiState.value = UiState.Loading.LoadFromNetwork
            try {
                val recentVersions = api.getRecentAndroidVersions()
                for (version in recentVersions) {
                    database.insert(version.mapToEntity())
                }
                uiState.value = UiState.Success(NETWORK, recentVersions)
            } catch (exception: Exception) {
                uiState.value = UiState.Error(NETWORK, "Network request failed")
            }
        }
    }

    fun clearDatabase() {
        viewModelScope.launch {
            database.clear()
        }
    }
}

enum class DataSource(val dataSourceName: String) {
    DATABASE("Database"),
    NETWORK("Network")
}