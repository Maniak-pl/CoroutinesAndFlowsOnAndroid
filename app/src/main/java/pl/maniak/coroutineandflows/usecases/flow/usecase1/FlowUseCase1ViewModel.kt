package pl.maniak.coroutineandflows.usecases.flow.usecase1

import androidx.lifecycle.LiveData
import pl.maniak.coroutineandflows.base.BaseViewModel

class FlowUseCase1ViewModel(
    stockPriceDataSource: StockPriceDataSource
) : BaseViewModel<UiState>() {

    val currentStockPriceAsLiveData: LiveData<UiState> = TODO()

}