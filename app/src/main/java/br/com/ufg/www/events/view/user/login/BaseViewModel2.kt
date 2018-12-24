package br.com.ufg.www.events.view.user.login


import androidx.databinding.ViewDataBinding
import androidx.lifecycle.MutableLiveData
import br.com.redcode.base.utils.Constants
import br.com.redcode.easyrestful.library.domain.BaseActivityMVVMRestful
import br.com.redcode.easyrestful.library.domain.BaseViewModelRestful
import br.com.ufg.www.events.BR
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

abstract class ActivityMVVM2<B : ViewDataBinding, VM : BaseViewModel2> : BaseActivityMVVMRestful<B, VM>() {

    override val idBRViewModel: Int = BR.viewModel

    override fun handleActionAPI(action: Int, id: String) {
        when (action) {
            Constants.ERROR_API_CLEAN_AND_FORCE_LOGIN -> clearLocalDataAndGoToLoginScreen()
            Constants.ERROR_API_TOKEN_EXPIRATION -> recreateToken()
            else -> super.handleActionAPI(action, id)
        }
    }

    open fun recreateToken() {}

}

abstract class BaseViewModelWithLiveData2<Model> : BaseViewModel2() {

    val liveData = MutableLiveData<Model>()
    var id = Constants.INVALID_VALUE

    abstract fun load()

}

abstract class BaseViewModel2 : BaseViewModelRestful(), CoroutineScope {

    val job = Job()
    override val coroutineContext: CoroutineContext
        get() = io()

    fun io() = job + Dispatchers.IO
    fun main() = job + Dispatchers.Main

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

}