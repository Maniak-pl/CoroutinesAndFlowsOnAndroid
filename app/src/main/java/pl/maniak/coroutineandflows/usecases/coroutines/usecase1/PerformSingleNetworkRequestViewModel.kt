package pl.maniak.coroutineandflows.usecases.coroutines.usecase1

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pl.maniak.coroutineandflows.base.BaseViewModel
import pl.maniak.coroutineandflows.mock.MockApi

class PerformSingleNetworkRequestViewModel(
    private val mockApi: MockApi = mockApi()
) : BaseViewModel<UiState>() {

    fun performSingleNetworkRequest() {
        uiState.value = UiState.Loading

        viewModelScope.launch {
            val recentAndroidVersion = mockApi.getRecentAndroidVersions()
            uiState.value = UiState.Success(recentAndroidVersion)
        }
    }
}