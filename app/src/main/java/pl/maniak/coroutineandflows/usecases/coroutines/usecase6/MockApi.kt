package pl.maniak.coroutineandflows.usecases.coroutines.usecase6

import com.google.gson.Gson
import pl.maniak.coroutineandflows.mock.createMockApi
import pl.maniak.coroutineandflows.mock.mockAndroidVersions
import pl.maniak.coroutineandflows.utils.MockNetworkInterceptor

fun mockApi() = createMockApi(
    MockNetworkInterceptor()
        .mock(
            "http://localhost/recent-android-versions",
            { "something went wrong on server side" },
            500,
            1000,
            persist = false
        ).mock(
            "http://localhost/recent-android-versions",
            { "something went wrong on server side" },
            500,
            1000,
            persist = false
        ).mock(
            "http://localhost/recent-android-versions",
            { Gson().toJson(mockAndroidVersions) },
            200,
            1000
        )
)