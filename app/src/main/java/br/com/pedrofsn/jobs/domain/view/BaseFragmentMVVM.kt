package br.com.pedrofsn.jobs.domain.view

import android.os.Bundle
import android.view.View
import br.com.pedrofsn.jobs.domain.extensions.observer
import br.com.pedrofsn.jobs.domain.network.NetworkErrorType

abstract class BaseFragmentMVVM : BaseFragment() {

    abstract val viewModel: BaseViewModel

    private val observerNetworkError = observer<NetworkErrorType> { onNetworkError(it) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.networkFeedback.eventNetworkError.observe(
            viewLifecycleOwner,
            observerNetworkError
        )
    }

    private fun onNetworkError(errorType: NetworkErrorType) = when (errorType) {
        is NetworkErrorType.ServerNotResponding -> onServerNotResponding()
        is NetworkErrorType.UnknownHost -> onUnknownHost()
        is NetworkErrorType.HttpError -> onNetworkHttpError(errorType.errorHandled)
        is NetworkErrorType.Timeout -> onNetworkTimeout()
        is NetworkErrorType.UnknownError -> onNetworkUnknownError(errorType.message)
    }
}