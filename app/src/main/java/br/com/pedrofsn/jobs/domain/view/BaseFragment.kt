package br.com.pedrofsn.jobs.domain.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.redcode.easyreftrofit.library.CallbackNetworkRequest
import br.com.redcode.easyreftrofit.library.model.ErrorHandled
import com.google.android.material.snackbar.Snackbar

abstract class BaseFragment : Fragment(), CallbackNetworkRequest {

    abstract val layout: Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layout, container, false)
    }

    override fun onNetworkError() {
        showError("onNetworkError")
    }

    override fun onNetworkHttpError(errorHandled: ErrorHandled) {
        showError("Erro ${errorHandled.networkError}")
    }

    override fun onNetworkTimeout() {
        showError("onNetworkTimeout")
    }

    override fun onNetworkUnknownError(message: String) {
        showError("onNetworkUnknownError: $message")
    }

    private fun showError(message: String) {
        val view = requireActivity().findViewById<View>(android.R.id.content)
        Snackbar.make(view, message, Snackbar.LENGTH_LONG).show()
    }
}