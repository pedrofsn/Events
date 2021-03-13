package br.com.pedrofsn.jobs.domain.view

import android.os.Bundle
import android.view.View
import androidx.lifecycle.asLiveData
import br.com.pedrofsn.network.data.NetworkErrorType

abstract class BaseFragmentMVVM : BaseFragment() {

    abstract val viewModel: BaseViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeNetworkError()
    }

    private fun observeNetworkError() = viewModel
        .networkFeedback
        .stateNetworkError
        .asLiveData()
        .observe(viewLifecycleOwner, this::onNetworkError)

    private fun onNetworkError(errorType: NetworkErrorType) {
        when (errorType) {
            is NetworkErrorType.ServerNotResponding -> onServerNotResponding()
            is NetworkErrorType.UnknownHost -> onUnknownHost()
            is NetworkErrorType.HttpError -> onNetworkHttpError(errorType.errorHandled)
            is NetworkErrorType.Timeout -> onNetworkTimeout()
            is NetworkErrorType.UnknownError -> onNetworkUnknownError(errorType.message)
        }
    }
}