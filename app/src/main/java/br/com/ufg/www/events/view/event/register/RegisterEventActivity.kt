package br.com.ufg.www.events.view.event.register

import android.app.Activity
import android.content.Intent
import android.view.View
import br.com.redcode.base.extensions.extract
import br.com.redcode.base.extensions.lazyId
import br.com.redcode.base.mvvm.domain.fragment.BaseFragmentMVVM
import br.com.redcode.easymask.handleDate
import br.com.redcode.easyrestful.library.impl.activity.ActivityMVVM
import br.com.redcode.easyvalidation.Validate
import br.com.ufg.www.events.R
import br.com.ufg.www.events.databinding.ActivityRegisterEventBinding
import br.com.ufg.www.events.view.places.add_map.BaseGoogleMapsActivity.Companion.CHANGE_LOCATION
import br.com.ufg.www.events.view.places.add_map.MapsSelectLocationActivity
import br.com.ufg.www.events.view.skill.SkillFragment
import br.com.ufg.www.events.view.skill.SkillViewModel
import com.google.android.gms.maps.model.LatLng

class RegisterEventActivity : ActivityMVVM<ActivityRegisterEventBinding, RegisterEventViewModel>() {

    override val classViewModel = RegisterEventViewModel::class.java
    override val layout = R.layout.activity_register_event

    private val id by lazyId()

    private val fragmentSkill by lazy { supportFragmentManager.findFragmentByTag("skillFragment") as SkillFragment }

    override fun afterOnCreate() {
        enableHomeAsUpActionBar()
        binding.editTextDate.handleDate()
        viewModel.id = id
        ((fragmentSkill as BaseFragmentMVVM<*, *>).viewModel as SkillViewModel).load(idEvent = id)
        viewModel.load()
    }

    fun changeLocation(view: View?) = goTo<MapsSelectLocationActivity>(CHANGE_LOCATION)

    fun save(view: View?) {
        if (Validate.isFilled(binding.editTextName, binding.editTextDate, binding.editTextLatitude, binding.editTextLongitude)) {

            val skillViewModel = ((fragmentSkill as BaseFragmentMVVM<*, *>).viewModel as SkillViewModel)

            if (skillViewModel.hasSelectedJobType()) {
                viewModel.save(skillViewModel.getSelectedJobTypes())
            } else {
                showMessage(getString(R.string.select_a_job_need))
            }
        }
    }

    override fun handleEvent(event: String, obj: Any?) {
        when (event) {
            "onRegistered" -> onRegistered()
            else -> super.handleEvent(event, obj)
        }
    }

    private fun onRegistered() = showSimpleAlert(getString(R.string.event_registered_successfully)) { finish() }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (CHANGE_LOCATION == requestCode && resultCode == Activity.RESULT_OK) {
            val latLng = data?.getParcelableExtra<LatLng>("latLng")
            val address = data?.getStringExtra("addressString")

            latLng?.let {
                viewModel.liveData.value?.latitude = it.latitude.toString()
                viewModel.liveData.value?.longitude = it.longitude.toString()
                viewModel.liveData.value?.address = extract safe address
                binding.executePendingBindings()
            }
        }
    }

}