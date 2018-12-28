package br.com.ufg.www.events.view.skill

import android.content.Context
import android.content.DialogInterface
import br.com.redcode.base.utils.Alerts
import br.com.redcode.easyrestful.library.fragment.FragmentMVVM
import br.com.ufg.www.events.R
import br.com.ufg.www.events.data.model.JobType
import br.com.ufg.www.events.databinding.FragmentSkillBinding
import br.com.ufg.www.events.extensions.showOrHide
import com.google.android.material.chip.Chip
import com.google.android.material.snackbar.Snackbar

class SkillFragment : FragmentMVVM<FragmentSkillBinding, SkillViewModel>() {

    override val classViewModel = SkillViewModel::class.java
    override val layout = R.layout.fragment_skill

    override fun afterOnCreate() {
        super.afterOnCreate()
        binding.imageViewAdd.setOnClickListener { addJob() }
    }

    fun addJob() {
        val selectableJobTypes = viewModel.getSelectableJobTypes()

        if (selectableJobTypes.isNotEmpty()) {
            val options = selectableJobTypes.map { it.description }

            val dialog = Alerts.getDialogSimpleList(
                    context = activity as Context,
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

    override fun handleEvent(event: String, obj: Any?) {
        when (event) {
            "addJobType" -> addJobType(obj)
            "removeJobType" -> if (obj != null && obj is JobType) removeChip(jobType = obj)
            "refreshVisibilityImageViewAdd" -> if (obj != null && obj is Boolean) refreshVisibilityImageViewAdd(obj)
            else -> super.handleEvent(event, obj)
        }
    }

    private fun addJobType(obj: Any?) {
        if (obj != null) {
            when (obj) {
                is JobType -> addChip(jobType = obj)
                is List<*> -> obj.forEach { addJobType(it as JobType) }
            }
        }
    }

    private fun addChip(jobType: JobType) {
        val viewChip = Chip(activity)
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

    private fun showFeedbackJobTypeRemoved(jobType: JobType) {
        val message = "'${jobType.description}' ${getString(R.string.removed)}"
        val snackbar = Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT)
        snackbar.setAction(getString(R.string.undo)) {
            viewModel.addJobType(jobType)
        }
        snackbar.show()
        viewModel.refreshVisibilityImageViewAdd()
    }

    private fun refreshVisibilityImageViewAdd(showOrHide: Boolean) = binding.imageViewAdd.showOrHide(showOrHide)

}