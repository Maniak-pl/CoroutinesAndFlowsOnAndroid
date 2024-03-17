package pl.maniak.coroutineandflows.usecases.coroutines.usecase9

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import pl.maniak.coroutineandflows.base.BaseActivity
import pl.maniak.coroutineandflows.base.useCase9Description
import pl.maniak.coroutineandflows.databinding.ActivityDebuggingcoroutinesBinding
import pl.maniak.coroutineandflows.utils.fromHtml
import pl.maniak.coroutineandflows.utils.setGone
import pl.maniak.coroutineandflows.utils.setVisible
import pl.maniak.coroutineandflows.utils.toast

class DebuggingCoroutinesActivity : BaseActivity() {

    override fun getToolbarTitle() = useCase9Description

    private val binding by lazy { ActivityDebuggingcoroutinesBinding.inflate(layoutInflater) }
    private val viewModel: DebuggingCoroutinesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        viewModel.uiState().observe(this, Observer { uiState ->
            if (uiState != null) {
                render(uiState)
            }
        })
        binding.btnPerformSingleNetworkRequest.setOnClickListener {
            viewModel.performSingleNetworkRequest()
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
        progressBar.setVisible()
        textViewResult.text = ""
        btnPerformSingleNetworkRequest.isEnabled = false
    }

    private fun onSuccess(uiState: UiState.Success) = with(binding) {
        progressBar.setGone()
        btnPerformSingleNetworkRequest.isEnabled = true
        val readableVersions = uiState.recentVersions.map { "API ${it.apiLevel}: ${it.name}" }
        textViewResult.text = fromHtml(
            "<b>Recent Android Versions</b><br>${readableVersions.joinToString(separator = "<br>")}"
        )
    }

    private fun onError(uiState: UiState.Error) = with(binding) {
        progressBar.setGone()
        btnPerformSingleNetworkRequest.isEnabled = true
        toast(uiState.message)
    }
}