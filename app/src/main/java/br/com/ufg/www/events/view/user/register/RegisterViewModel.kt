package br.com.ufg.www.events.view.user.register

import br.com.redcode.base.utils.Constants.EMPTY_STRING
import br.com.redcode.easyrestful.library.impl.viewmodel.BaseViewModelWithLiveData
import br.com.ufg.www.events.App
import br.com.ufg.www.events.R
import br.com.ufg.www.events.data.offline.interactor.InteractorRegisterUser
import br.com.ufg.www.events.data.ui.InputUser
import br.com.ufg.www.events.extensions.isValid
import kotlinx.coroutines.launch

class RegisterViewModel : BaseViewModelWithLiveData<InputUser>() {

    private val interactor = InteractorRegisterUser()
    private val MINIMAL_LENGTH_PASSWORD = 3

    override fun load() = liveData.postValue(InputUser(EMPTY_STRING))

    fun register() {
        val inputUser = liveData.value
        inputUser?.apply {
            when {
                password.length < MINIMAL_LENGTH_PASSWORD -> showSimpleAlert(String.format(App.getContext()?.getString(R.string.passwords_need_minimal_x_characters)!!, MINIMAL_LENGTH_PASSWORD))
                password != confirmation -> showSimpleAlert(App.getContext()?.getString(R.string.password_doesnt_match)!!)
                else -> {
                    launch(coroutineContext) {
                        val id = interactor.register(inputUser).await()
                        if (id.isValid()) {
                            App.userLoggedIn = login
                            sendEventToUI("onRegistered")
                        }
                    }
                }
            }
        }
    }
}