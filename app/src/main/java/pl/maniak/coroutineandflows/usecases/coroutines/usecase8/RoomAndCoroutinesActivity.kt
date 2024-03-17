package pl.maniak.coroutineandflows.usecases.coroutines.usecase8

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import pl.maniak.coroutineandflows.R
import pl.maniak.coroutineandflows.base.BaseActivity
import pl.maniak.coroutineandflows.base.useCase8Description
import pl.maniak.coroutineandflows.databinding.ActivityQueryfromroomdatabaseBinding
import pl.maniak.coroutineandflows.utils.fromHtml
import pl.maniak.coroutineandflows.utils.setGone
import pl.maniak.coroutineandflows.utils.setVisible
import pl.maniak.coroutineandflows.utils.toast

class RoomAndCoroutinesActivity : BaseActivity() {

    override fun getToolbarTitle() = useCase8Description

    private val binding by lazy { ActivityQueryfromroomdatabaseBinding.inflate(layoutInflater) }

    private val viewModel: RoomAndCoroutinesViewModel by viewModels {
        ViewModelFactory(
            mockApi(),
            AndroidVersionDatabase.getInstance(applicationContext).androidVersionDao()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel.uiState().observe(this, Observer { uiState ->
            if (uiState != null) {
                render(uiState)
            }
        })
        binding.btnLoadData.setOnClickListener {
            viewModel.loadData()
        }
        binding.btnClearDatabase.setOnClickListener {
            viewModel.clearDatabase()
        }
    }

    private fun render(uiState: UiState) {
        when (uiState) {
            is UiState.Loading -> {
                onLoad(uiState)
            }
            is UiState.Success -> {
                onSuccess(uiState)
            }
            is UiState.Error -> {
                onError(uiState)
            }
        }
    }

    private fun onLoad(loadingState: UiState.Loading) = with(binding) {
        when (loadingState) {
            UiState.Loading.LoadFromDb -> {
                progressBarLoadFromDb.setVisible()
                textViewLoadFromDatabase.setVisible()
                imageViewDatabaseLoadSuccessOrError.setGone()
            }
            UiState.Loading.LoadFromNetwork -> {
                progressBarLoadFromNetwork.setVisible()
                textViewLoadFromNetwork.setVisible()
                imageViewNetworkLoadSuccessOrError.setGone()
            }
        }
    }

    private fun onSuccess(uiState: UiState.Success) = with(binding) {
        when (uiState.dataSource) {
            DataSource.NETWORK -> {
                progressBarLoadFromNetwork.setGone()
                imageViewNetworkLoadSuccessOrError.setImageDrawable(getDrawable(R.drawable.ic_check_green_24dp))
                imageViewNetworkLoadSuccessOrError.setVisible()
            }
            DataSource.DATABASE -> {
                progressBarLoadFromDb.setGone()
                imageViewDatabaseLoadSuccessOrError.setImageDrawable(getDrawable(R.drawable.ic_check_green_24dp))
                imageViewDatabaseLoadSuccessOrError.setVisible()
            }
        }

        val readableVersions = uiState.recentVersions.map { "API ${it.apiLevel}: ${it.name}" }
        textViewResult.text = fromHtml(
            "<b>Recent Android Versions [from ${uiState.dataSource.name}]</b><br>${readableVersions.joinToString(
                separator = "<br>"
            )}"
        )
    }

    private fun onError(uiState: UiState.Error) = with(binding) {
        when (uiState.dataSource) {
            DataSource.NETWORK -> {
                progressBarLoadFromNetwork.setGone()
                imageViewNetworkLoadSuccessOrError.setImageDrawable(getDrawable(R.drawable.ic_clear_red_24dp))
                imageViewNetworkLoadSuccessOrError.setVisible()
            }
            DataSource.DATABASE -> {
                progressBarLoadFromDb.setGone()
                imageViewDatabaseLoadSuccessOrError.setImageDrawable(getDrawable(R.drawable.ic_clear_red_24dp))
                imageViewDatabaseLoadSuccessOrError.setVisible()
            }
        }
        toast(uiState.message)
    }
}