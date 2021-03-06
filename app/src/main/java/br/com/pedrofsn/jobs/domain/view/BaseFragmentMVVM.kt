package br.com.pedrofsn.jobs.domain.view

import android.os.Bundle
import android.view.View
import br.com.pedrofsn.jobs.domain.extensions.observer
import br.com.pedrofsn.jobs.domain.network.NetworkError

abstract class BaseFragmentMVVM : BaseFragment() {

    abstract val viewModel: BaseViewModel

    private val observerNetworkError = observer<NetworkError> { onNetworkError(it) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.eventNetworkError.observe(viewLifecycleOwner, observerNetworkError)
    }

    private fun onNetworkError(error: NetworkError) = when (error) {
        is NetworkError.GenericError -> onNetworkError()
        is NetworkError.HttpError -> onNetworkHttpError(error.errorHandled)
        is NetworkError.Timeout -> onNetworkTimeout()
        is NetworkError.UnknownError -> onNetworkUnknownError(error.message)
    }
}