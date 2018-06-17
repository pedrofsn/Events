package br.com.ufg.www.places.mvp.places.register

import android.os.Bundle
import android.view.View
import br.com.ufg.www.places.App
import br.com.ufg.www.places.R
import br.com.ufg.www.places.domain.BaseActivity
import br.com.ufg.www.places.extensions.getString
import br.com.ufg.www.places.extensions.isFilled
import br.com.ufg.www.places.model.Place
import kotlinx.android.synthetic.main.activity_register_place.*

class RegisterPlaceActivity : BaseActivity(), View.OnClickListener, Contract.View {

    private val presenter = Presenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setContentView(R.layout.activity_register_place)
        buttonRegister.setOnClickListener(this)
    }

    override fun register() {
        if (editTextLatitude.isFilled() && editTextLongitude.isFilled()) {
            val latitude = editTextLatitude.getString()
            val longitude = editTextLongitude.getString()
            val description = editTextDescription.getString()

            val place = Place(
                    latitude = latitude,
                    longitude = longitude,
                    description = description,
                    login = App.userLoggedIn
            )

            presenter.register(place)
        }
    }

    override fun onRegistered() = finish()

    override fun onClick(v: View?) {
        v?.let {
            when (v.id) {
                buttonRegister.id -> register()
            }
        }
    }
}