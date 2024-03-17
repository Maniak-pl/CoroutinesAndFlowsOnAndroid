package pl.maniak.coroutineandflows.usecases.coroutines.usecase4

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import pl.maniak.coroutineandflows.R
import pl.maniak.coroutineandflows.base.BaseActivity
import pl.maniak.coroutineandflows.base.useCase4Description
import pl.maniak.coroutineandflows.databinding.ActivityPerformvariableamountofnetworkrequestsconcurrentlyBinding
import pl.maniak.coroutineandflows.utils.fromHtml
import pl.maniak.coroutineandflows.utils.setGone
import pl.maniak.coroutineandflows.utils.setVisible
import pl.maniak.coroutineandflows.utils.toast

class VariableAmountOfNetworkRequestsActivity : BaseActivity() {

    private val binding by lazy {
        ActivityPerformvariableamountofnetworkrequestsconcurrentlyBinding.inflate(
            layoutInflater
        )
    }

    private val viewModel: VariableAmountOfNetworkRequestsViewModel by viewModels()

    override fun getToolbarTitle() = useCase4Description

    private var operationStartTime = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel.uiState().observe(this, Observer { uiState ->
            if (uiState != null) {
                render(uiState)
            }
        })

        binding.btnRequestsSequentially.setOnClickListener {
            viewModel.performNetworkRequestsSequentially()
        }

        binding.btnRequestsConcurrently.setOnClickListener {
            viewModel.performNetworkRequestsConcurrently()
        }

    }

    private fun render(uiState: UiState) {
        when (uiState) {
            is UiState.Loading -> {
                onLoad()
            }
            is UiState.Success -> {
                onSuccess(uiState)
            }
            is UiState.Error -> {
                onError(uiState)
            }
        }
    }

    private fun onLoad() = with(binding) {
        operationStartTime = System.currentTimeMillis()
        progressBar.setVisible()
        textViewResult.text = ""
        textViewDuration.text = ""
        disableButtons()
    }

    private fun onSuccess(uiState: UiState.Success) = with(binding) {
        enableButtons()
        progressBar.setGone()
        val duration = System.currentTimeMillis() - operationStartTime
        textViewDuration.text = getString(R.string.duration, duration)

        val versionFeatures = uiState.versionFeatures
        val versionFeaturesString = versionFeatures.map {
            "<b>New Features of ${it.androidVersion.name} </b> <br> ${it.features.joinToString(
                prefix = "- ",
                separator = "<br>- "
            )}"
        }.joinToString(separator = "<br><br>")

        textViewResult.text = fromHtml(versionFeaturesString)
    }

    private fun onError(uiState: UiState.Error) = with(binding) {
        progressBar.setGone()
        toast(uiState.message)
        enableButtons()
    }

    private fun enableButtons() = with(binding) {
        btnRequestsSequentially.isEnabled = true
        btnRequestsConcurrently.isEnabled = true
    }

    private fun disableButtons() = with(binding) {
        btnRequestsSequentially.isEnabled = false
        btnRequestsConcurrently.isEnabled = false
    }
}