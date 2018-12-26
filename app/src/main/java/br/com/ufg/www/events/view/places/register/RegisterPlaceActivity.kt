package br.com.ufg.www.events.view.places.register

import android.view.View
import br.com.redcode.easyrestful.library.impl.activity.ActivityMVVM
import br.com.ufg.www.events.R
import br.com.ufg.www.events.databinding.ActivityRegisterPlaceBinding
import br.com.ufg.www.events.extensions.isFilled

class RegisterPlaceActivity : ActivityMVVM<ActivityRegisterPlaceBinding, RegisterPlaceViewModel>() {

    override val classViewModel = RegisterPlaceViewModel::class.java
    override val layout = R.layout.activity_register_place

    override fun afterOnCreate() {
        enableHomeAsUpActionBar()
    }

    fun register(view: View?) {
        if (binding.editTextLatitude.isFilled() && binding.editTextLongitude.isFilled()) {
            viewModel.register()
        }
    }

    override fun handleEvent(event: String, obj: Any?) {
        when (event) {
            "onSuccess" -> finish()
            else -> super.handleEvent(event, obj)
        }
    }

}