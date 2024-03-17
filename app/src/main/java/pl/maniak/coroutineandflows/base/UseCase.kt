package pl.maniak.coroutineandflows.base

import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity
import pl.maniak.coroutineandflows.usecases.coroutines.usecase1.PerformSingleNetworkRequestActivity
import pl.maniak.coroutineandflows.usecases.coroutines.usecase10.CalculationInBackgroundActivity
import pl.maniak.coroutineandflows.usecases.coroutines.usecase11.CooperativeCancellationActivity
import pl.maniak.coroutineandflows.usecases.coroutines.usecase12.CalculationInSeveralCoroutinesActivity
import pl.maniak.coroutineandflows.usecases.coroutines.usecase13.ExceptionHandlingActivity
import pl.maniak.coroutineandflows.usecases.coroutines.usecase14.ContinueCoroutineWhenUserLeavesScreenActivity
import pl.maniak.coroutineandflows.usecases.coroutines.usecase15.WorkManagerActivity
import pl.maniak.coroutineandflows.usecases.coroutines.usecase16.PerformanceAnalysisActivity
import pl.maniak.coroutineandflows.usecases.coroutines.usecase17.PerformCalculationOnMainThreadActivity
import pl.maniak.coroutineandflows.usecases.coroutines.usecase2.Perform2SequentialNetworkRequestsActivity
import pl.maniak.coroutineandflows.usecases.coroutines.usecase2.callbacks.SequentialNetworkRequestsCallbacksActivity
import pl.maniak.coroutineandflows.usecases.coroutines.usecase2.rx.SequentialNetworkRequestsRxActivity
import pl.maniak.coroutineandflows.usecases.coroutines.usecase3.PerformNetworkRequestsConcurrentlyActivity
import pl.maniak.coroutineandflows.usecases.coroutines.usecase4.VariableAmountOfNetworkRequestsActivity
import pl.maniak.coroutineandflows.usecases.coroutines.usecase5.NetworkRequestWithTimeoutActivity
import pl.maniak.coroutineandflows.usecases.coroutines.usecase6.RetryNetworkRequestActivity
import pl.maniak.coroutineandflows.usecases.coroutines.usecase7.TimeoutAndRetryActivity
import pl.maniak.coroutineandflows.usecases.coroutines.usecase7.callbacks.TimeoutAndRetryCallbackActivity
import pl.maniak.coroutineandflows.usecases.coroutines.usecase7.rx.TimeoutAndRetryRxActivity
import pl.maniak.coroutineandflows.usecases.coroutines.usecase8.RoomAndCoroutinesActivity
import pl.maniak.coroutineandflows.usecases.coroutines.usecase9.DebuggingCoroutinesActivity
import pl.maniak.coroutineandflows.usecases.flow.usecase1.FlowUseCase1Activity
import pl.maniak.coroutineandflows.usecases.flow.usecase2.FlowUseCase2Activity
import pl.maniak.coroutineandflows.usecases.flow.usecase3.FlowUseCase3Activity
import pl.maniak.coroutineandflows.usecases.flow.usecase4.FlowUseCase4Activity
import kotlinx.parcelize.Parcelize

@Parcelize
data class UseCase(
    val description: String,
    val targetActivity: Class<out AppCompatActivity>
) : Parcelable

@Parcelize
data class UseCaseCategory(val categoryName: String, val useCases: List<UseCase>) : Parcelable

const val useCase1Description = "#1 Perform single network request"
const val useCase2Description = "#2 Perform two sequential network requests"
const val useCase2UsingCallbacksDescription = "#2 using Callbacks"
const val useCase2UsingRxDescription = "#2 using RxJava"
const val useCase3Description = "#3 Perform several network requests concurrently"
const val useCase4Description = "#4 Perform variable amount of network requests"
const val useCase5Description = "#5 Network request with TimeOut"
const val useCase6Description = "#6 Retry Network request"
const val useCase7Description = "#7 Network requests with timeout and retry"
const val useCase7UsingCallbacksDescription = "#7 Using callbacks"
const val useCase7UsingRxDescription = "#7 Using RxJava"
const val useCase8Description = "#8 Room and Coroutines"
const val useCase9Description = "#9 Debugging Coroutines"
const val useCase10Description = "#10 Offload expensive calculation to background thread"
const val useCase11Description = "#11 Cooperative Cancellation"
const val useCase12Description = "#12 Offload expensive calculation to several coroutines"
const val useCase13Description = "#13 Exception Handling"
const val useCase14Description = "#14 Continue Coroutine when User leaves screen"
const val useCase15Description = "#15 Using WorkManager with Coroutines"
const val useCase16Description =
    "#16 Performance Analysis of dispatchers, number of coroutines and yielding"
const val useCase17Description =
    "#17 Perform heavy calculation on Main Thread without freezing the UI"

private val coroutinesUseCases =
    UseCaseCategory(
        "Coroutine Use Cases", listOf(
            UseCase(
                useCase1Description,
                PerformSingleNetworkRequestActivity::class.java
            ),
            UseCase(
                useCase2Description,
                Perform2SequentialNetworkRequestsActivity::class.java
            ),
            UseCase(
                useCase2UsingCallbacksDescription,
                SequentialNetworkRequestsCallbacksActivity::class.java
            ), UseCase(
                useCase2UsingRxDescription,
                SequentialNetworkRequestsRxActivity::class.java
            ),
            UseCase(
                useCase3Description,
                PerformNetworkRequestsConcurrentlyActivity::class.java
            ),
            UseCase(
                useCase4Description,
                VariableAmountOfNetworkRequestsActivity::class.java
            ),
            UseCase(
                useCase5Description,
                NetworkRequestWithTimeoutActivity::class.java
            ),
            UseCase(
                useCase6Description,
                RetryNetworkRequestActivity::class.java
            ),
            UseCase(
                useCase7Description,
                TimeoutAndRetryActivity::class.java
            ),
            UseCase(
                useCase7UsingCallbacksDescription,
                TimeoutAndRetryCallbackActivity::class.java
            ), UseCase(
                useCase7UsingRxDescription,
                TimeoutAndRetryRxActivity::class.java
            ),
            UseCase(
                useCase8Description,
                RoomAndCoroutinesActivity::class.java
            ),
            UseCase(
                useCase9Description,
                DebuggingCoroutinesActivity::class.java
            ),
            UseCase(
                useCase10Description,
                CalculationInBackgroundActivity::class.java
            ),
            UseCase(
                useCase11Description,
                CooperativeCancellationActivity::class.java
            ),
            UseCase(
                useCase12Description,
                CalculationInSeveralCoroutinesActivity::class.java
            ),
            UseCase(
                useCase13Description,
                ExceptionHandlingActivity::class.java
            ),
            UseCase(
                useCase14Description,
                ContinueCoroutineWhenUserLeavesScreenActivity::class.java
            ),
            UseCase(
                useCase15Description,
                WorkManagerActivity::class.java
            ),
            UseCase(
                useCase16Description,
                PerformanceAnalysisActivity::class.java
            ),
            UseCase(
                useCase17Description,
                PerformCalculationOnMainThreadActivity::class.java
            )
        )
    )

const val flowUseCase1Description = "#1 Flow Basics"
const val flowUseCase2Description = "#2 Basic Intermediate operators"
const val flowUseCase3Description = "#3 Exception Handling"
const val flowUseCase4Description = "#4 Exposing Flows in the ViewModel"

private val flowUseCases =
    UseCaseCategory(
        "Flow Use Cases",
        listOf(
            UseCase(
                flowUseCase1Description,
                FlowUseCase1Activity::class.java
            ), UseCase(
                flowUseCase2Description,
                FlowUseCase2Activity::class.java
            ), UseCase(
                flowUseCase3Description,
                FlowUseCase3Activity::class.java
            ), UseCase(
                flowUseCase4Description,
                FlowUseCase4Activity::class.java
            )
        )
    )

val useCaseCategories = listOf(
    coroutinesUseCases,
    flowUseCases
)