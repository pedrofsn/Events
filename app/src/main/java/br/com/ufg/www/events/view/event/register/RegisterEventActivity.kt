package br.com.ufg.www.events.view.event.register

import android.app.Activity
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.view.View
import br.com.redcode.base.extensions.extract
import br.com.redcode.base.extensions.lazyId
import br.com.redcode.base.mvvm.domain.fragment.BaseFragmentMVVM
import br.com.redcode.base.utils.Alerts
import br.com.redcode.easyrestful.library.impl.activity.ActivityMVVM
import br.com.redcode.easyvalidation.Validate
import br.com.ufg.www.events.R
import br.com.ufg.www.events.databinding.ActivityRegisterEventBinding
import br.com.ufg.www.events.view.places.add_map.BaseGoogleMapsActivity.Companion.CHANGE_LOCATION
import br.com.ufg.www.events.view.places.add_map.MapsSelectLocationActivity
import br.com.ufg.www.events.view.skill.SkillFragment
import br.com.ufg.www.events.view.skill.SkillViewModel
import com.google.android.gms.maps.model.LatLng
import java.util.*

class RegisterEventActivity : ActivityMVVM<ActivityRegisterEventBinding, RegisterEventViewModel>() {

    override val classViewModel = RegisterEventViewModel::class.java
    override val layout = R.layout.activity_register_event

    private val id by lazyId()

    private val fragmentSkill by lazy { supportFragmentManager.findFragmentByTag("skillFragment") as SkillFragment }

    override fun afterOnCreate() {
        enableHomeAsUpActionBar()
        viewModel.id = id
        viewModel.load()
        loadSkills()
    }

    private fun loadSkills() = viewModel.loadSkills { ((fragmentSkill as BaseFragmentMVVM<*, *>).viewModel as SkillViewModel).load(it) }

    private fun changeDate(calendar: Calendar?, callback: () -> Unit) {
        calendar?.let {
            val callbackDatePicler = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                val callbackTimePicker = TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
                    calendar.set(year, month, dayOfMonth, hourOfDay, minute)
                    callback.invoke()
                    binding.executePendingBindings()
                }

                Alerts.showTimePicker(
                        context = this,
                        callback = callbackTimePicker,
                        calendar = calendar
                )
            }

            Alerts.showDatePicker(
                    context = this,
                    callback = callbackDatePicler,
                    calendar = calendar
            )
        }
    }

    fun changeDateStart(view: View?) = viewModel.liveData.value?.apply { changeDate(calendarStart) { refreshDateStart() } }
    fun changeDateEnd(view: View?) = viewModel.liveData.value?.apply { changeDate(calendarEnd) { refreshDateEnd() } }
    fun changeLocation(view: View?) = goTo<MapsSelectLocationActivity>(CHANGE_LOCATION)

    fun save(view: View?) {
        if (Validate.isFilled(binding.editTextName, binding.editTextDateStart, binding.editTextDateEnd)) {
            val skillViewModel = ((fragmentSkill as BaseFragmentMVVM<*, *>).viewModel as SkillViewModel)

            when {
                viewModel.liveData.value?.isDateStartAfterEnd() == true -> showMessage(getString(R.string.date_max_min_limit))
                viewModel.liveData.value?.isSameDates() == true -> showMessage(getString(R.string.date_equals))
                viewModel.liveData.value?.haveNotPlace() == true -> showMessage(getString(R.string.add_a_place))
                skillViewModel.hasSelected().not() -> showMessage(getString(R.string.select_a_job_need))
                else -> viewModel.save(skillViewModel.getSelecteds())
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