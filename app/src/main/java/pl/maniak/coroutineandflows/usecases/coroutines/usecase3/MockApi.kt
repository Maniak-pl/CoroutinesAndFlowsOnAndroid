package pl.maniak.coroutineandflows.usecases.coroutines.usecase3

import com.google.gson.Gson
import pl.maniak.coroutineandflows.mock.createMockApi
import pl.maniak.coroutineandflows.mock.mockVersionFeaturesAndroid10
import pl.maniak.coroutineandflows.mock.mockVersionFeaturesOreo
import pl.maniak.coroutineandflows.mock.mockVersionFeaturesPie
import pl.maniak.coroutineandflows.utils.MockNetworkInterceptor

fun mockApi() = createMockApi(
    MockNetworkInterceptor()
        .mock(
            "http://localhost/android-version-features/27",
            { Gson().toJson(mockVersionFeaturesOreo) },
            200,
            1000
        )
        .mock(
            "http://localhost/android-version-features/28",
            { Gson().toJson(mockVersionFeaturesPie) },
            200,
            1000
        )
        .mock(
            "http://localhost/android-version-features/29",
            { Gson().toJson(mockVersionFeaturesAndroid10) },
            200,
            1000
        )
)