package pl.maniak.coroutineandflows.usecases.coroutines.usecase7.rx

import com.google.gson.Gson
import pl.maniak.coroutineandflows.mock.AndroidVersion
import pl.maniak.coroutineandflows.mock.VersionFeatures
import pl.maniak.coroutineandflows.mock.mockVersionFeaturesOreo
import pl.maniak.coroutineandflows.mock.mockVersionFeaturesPie
import pl.maniak.coroutineandflows.utils.MockNetworkInterceptor
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

fun mockApi(): RxMockApi = createMockApi(
    MockNetworkInterceptor()
        // timeout on first request for oreo features
        .mock(
            "http://localhost/android-version-features/27",
            { Gson().toJson(mockVersionFeaturesOreo) },
            200,
            1050,
            persist = false
        )
        // network error on second request
        .mock(
            "http://localhost/android-version-features/27",
            { "Something went wrong on servers side" },
            500,
            200,
            persist = false
        )
        // 3rd request is successful and within timeout
        .mock(
            "http://localhost/android-version-features/27",
            { Gson().toJson(mockVersionFeaturesOreo) },
            200,
            100
        )
        // timeout on first request for pie features
        .mock(
            "http://localhost/android-version-features/28",
            { Gson().toJson(mockVersionFeaturesPie) },
            200,
            1050,
            persist = false
        )
        // network error on second request
        .mock(
            "http://localhost/android-version-features/28",
            { "Something went wrong on servers side" },
            500,
            200,
            persist = false
        )
        // 3rd request is successful and within timeout
        .mock(
            "http://localhost/android-version-features/28",
            { Gson().toJson(mockVersionFeaturesPie) },
            200,
            100
        )
)

interface RxMockApi {

    @GET("recent-android-versions")
    fun getRecentAndroidVersions(): Single<List<AndroidVersion>>

    @GET("android-version-features/{apiLevel}")
    fun getAndroidVersionFeatures(@Path("apiLevel") apiLevel: Int): Single<VersionFeatures>
}

fun createMockApi(interceptor: MockNetworkInterceptor): RxMockApi {
    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()

    val retrofit = Retrofit.Builder()
        .baseUrl("http://localhost/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
        .build()

    return retrofit.create(RxMockApi::class.java)
}