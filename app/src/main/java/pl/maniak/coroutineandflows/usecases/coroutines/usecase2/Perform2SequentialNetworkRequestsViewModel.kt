package pl.maniak.coroutineandflows.usecases.coroutines.usecase2

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pl.maniak.coroutineandflows.base.BaseViewModel
import pl.maniak.coroutineandflows.mock.MockApi

class Perform2SequentialNetworkRequestsViewModel(
    private val mockApi: MockApi = mockApi()
) : BaseViewModel<UiState>() {

    fun perform2SequentialNetworkRequest() {
        uiState.value = UiState.Loading

        viewModelScope.launch {
            try {
                val androidVersions = mockApi.getRecentAndroidVersions()
                val recentVersion = androidVersions.last()
                val featureVersions = mockApi.getAndroidVersionFeatures(recentVersion.apiLevel)

                uiState.value = UiState.Success(featureVersions)
            } catch (e: Exception) {
                uiState.value = UiState.Error("Network request failed")
            }
        }
    }
}