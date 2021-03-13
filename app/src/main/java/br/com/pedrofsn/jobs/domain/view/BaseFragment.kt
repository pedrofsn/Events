package br.com.pedrofsn.jobs.domain.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.pedrofsn.jobs.R
import br.com.pedrofsn.jobs.domain.extensions.toLogcat
import br.com.pedrofsn.network.CallbackNetworkRequest
import br.com.pedrofsn.network.data.ErrorHandled
import com.google.android.material.snackbar.Snackbar
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

abstract class BaseFragment : Fragment(), CoroutineScope, CallbackNetworkRequest {

    abstract val layout: Int
    override val coroutineContext: CoroutineContext = Dispatchers.IO

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layout, container, false)
    }

    override fun onServerNotResponding() {
        showError(getString(R.string.network_error_server_not_responding))
    }

    override fun onUnknownHost() {
        showError(getString(R.string.network_error_unknown_host_excpetion))
    }

    override fun onNetworkHttpError(errorHandled: ErrorHandled) {
        showError(getString(R.string.network_error_http_x, errorHandled.statusCode))
    }

    override fun onNetworkTimeout() {
        showError(getString(R.string.network_error_timeout))
    }

    override fun onNetworkUnknownError(message: String) {
        showError(getString(R.string.network_error_unknown))
        message.toLogcat()
    }

    private fun showError(message: String) {
        val view = requireActivity().findViewById<View>(android.R.id.content)
        Snackbar.make(view, message, Snackbar.LENGTH_LONG).show()
    }
}