package pl.maniak.coroutineandflows.usecases.flow.usecase1

import android.content.Context
import com.google.gson.Gson
import pl.maniak.coroutineandflows.usecases.flow.mock.createFlowMockApi
import pl.maniak.coroutineandflows.usecases.flow.mock.fakeCurrentStockPrices
import pl.maniak.coroutineandflows.utils.MockNetworkInterceptor

fun mockApi(context: Context) =
    createFlowMockApi(
        MockNetworkInterceptor()
            .mock(
                path = "/current-stock-prices",
                body = { Gson().toJson(fakeCurrentStockPrices(context)) },
                status = 200,
                delayInMs = 1500,
            )
    )