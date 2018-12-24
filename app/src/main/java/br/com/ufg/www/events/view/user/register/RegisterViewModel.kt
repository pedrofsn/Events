package br.com.ufg.www.events.view.user.register

import br.com.redcode.base.utils.Constants.EMPTY_STRING
import br.com.redcode.easyrestful.library.impl.viewmodel.BaseViewModelWithLiveData
import br.com.ufg.www.events.App
import br.com.ufg.www.events.R
import br.com.ufg.www.events.extensions.isValid
import br.com.ufg.www.events.model.ui.InputUser

class RegisterViewModel : BaseViewModelWithLiveData<InputUser>() {

    private val interactor = InteractorRegisterUser()
    private val MINIMAL_LENGTH_PASSWORD = 3

    override fun load() = liveData.postValue(InputUser(EMPTY_STRING))

    fun register() {
        liveData.value?.apply {
            when {
                password.length < MINIMAL_LENGTH_PASSWORD -> showSimpleAlert(String.format(App.instance.getString(R.string.passwords_need_minimal_x_characters), MINIMAL_LENGTH_PASSWORD))
                password != confirmation -> showSimpleAlert(App.instance.getString(R.string.password_doesnt_match))
                else -> {
//                    presenter.register(registerUser)
                }
            }

        }

        val callback = { id: Long ->
            if (id.isValid()) {
//                App.userLoggedIn = registerUser.login
                sendEventToUI("onRegistered")
            }
        }

//        interactor.register(registerUser, callback).start()
    }
}