package br.com.ufg.www.events.view.event

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.view.View
import br.com.redcode.base.extensions.extract
import br.com.redcode.base.utils.Alerts
import br.com.redcode.easymask.handleDate
import br.com.redcode.easyvalidation.Validate
import br.com.ufg.www.events.R
import br.com.ufg.www.events.databinding.ActivityEventRegisterBinding
import br.com.ufg.www.events.domain.ActivityMVVM2
import br.com.ufg.www.events.extensions.showOrHide
import br.com.ufg.www.events.model.JobType
import br.com.ufg.www.events.view.places.add_map.BaseGoogleMapsActivity.Companion.CHANGE_LOCATION
import br.com.ufg.www.events.view.places.add_map.MapsSelectLocationActivity
import com.google.android.gms.maps.model.LatLng
import com.google.android.material.chip.Chip
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.snackbar.Snackbar.LENGTH_SHORT

class EventRegisterActivity : ActivityMVVM2<ActivityEventRegisterBinding, EventRegisterViewModel>() {

    override val classViewModel = EventRegisterViewModel::class.java
    override val layout = R.layout.activity_event_register

    override fun afterOnCreate() {
        enableHomeAsUpActionBar()
        binding.editTextDate.handleDate()
        viewModel.load()
    }

    fun addJob(view: View?) {
        val selectableJobTypes = viewModel.getSelectableJobTypes()

        if (selectableJobTypes.isNotEmpty()) {
            val options = selectableJobTypes.map { it.description }

            val dialog = Alerts.getDialogSimpleList(
                    context = this,
                    options = options,
                    callback = DialogInterface.OnClickListener { dialog, which ->
                        dialog.dismiss()
                        val selectedJobType = viewModel.getSelectableJobTypes()[which]
                        viewModel.addJobType(selectedJobType)
                    }
            )
            dialog.setTitle(R.string.job_needs)
            dialog.show()
        } else {
            showSimpleAlert(getString(R.string.you_reached_max_limit))
        }
    }

    fun changeLocation(view: View?) = goTo<MapsSelectLocationActivity>(CHANGE_LOCATION)

    fun save(view: View?) {
        if (Validate.isFilled(binding.editTextName, binding.editTextDate, binding.editTextLatitude, binding.editTextLongitude)) {
            if (viewModel.hasSelectedJobType()) {
                viewModel.save()
            } else {
                showMessage(getString(R.string.select_a_job_need))
            }
        }
    }

    override fun handleEvent(event: String, obj: Any?) {
        when (event) {
            "addJobType" -> if (obj != null && obj is JobType) addChip(jobType = obj)
            "removeJobType" -> if (obj != null && obj is JobType) removeChip(jobType = obj)
            "refreshVisibilityImageViewAdd" -> if (obj != null && obj is Boolean) refreshVisibilityImageViewAdd(obj)
            else -> super.handleEvent(event, obj)
        }
    }

    private fun addChip(jobType: JobType) {
        showFeedbackJobTypeAdded(jobType)

        val viewChip = Chip(this)
        viewChip.text = jobType.description
        viewChip.tag = jobType.id
        viewChip.isCheckable = false
        viewChip.isCloseIconEnabled = true
        viewChip.setOnCloseIconClickListener { viewModel.removeJobType(jobType) }

        binding.chipGroup.addView(viewChip)
    }

    private fun removeChip(jobType: JobType) {
        showFeedbackJobTypeRemoved(jobType)
        val viewChip = binding.chipGroup.findViewWithTag<Chip>(jobType.id)
        binding.chipGroup.removeView(viewChip)
    }

    private fun showFeedbackJobTypeAdded(jobType: JobType) {
        val message = "'${jobType.description}' ${getString(R.string.added)}"
        showMessage(message)
        viewModel.refreshVisibilityImageViewAdd()
    }

    private fun showFeedbackJobTypeRemoved(jobType: JobType) {
        val message = "'${jobType.description}' ${getString(R.string.removed)}"
        val snackbar = Snackbar.make(findViewById<View>(android.R.id.content), message, LENGTH_SHORT)
        snackbar.setAction(getString(R.string.undo)) {
            viewModel.addJobType(jobType)
        }
        snackbar.show()
        viewModel.refreshVisibilityImageViewAdd()
    }

    fun refreshVisibilityImageViewAdd(showOrHide: Boolean) = binding.imageViewAdd.showOrHide(showOrHide)

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