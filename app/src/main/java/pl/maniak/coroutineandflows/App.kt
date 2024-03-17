package pl.maniak.coroutineandflows

import android.app.Application
import pl.maniak.coroutineandflows.usecases.coroutines.usecase14.AndroidVersionDatabase
import pl.maniak.coroutineandflows.usecases.coroutines.usecase14.AndroidVersionRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import timber.log.Timber

class App : Application() {

    private val applicationScope = CoroutineScope(SupervisorJob())

    val androidVersionRepository by lazy {
        val database = AndroidVersionDatabase.getInstance(applicationContext).androidVersionDao()
        AndroidVersionRepository(
            database,
            applicationScope
        )
    }

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())

        // Enable Debugging for Kotlin Coroutines in debug builds
        // Prints Coroutine name when logging Thread.currentThread().name
        System.setProperty("kotlinx.coroutines.debug", if (pl.maniak.coroutineandflows.BuildConfig.DEBUG) "on" else "off")
    }
}