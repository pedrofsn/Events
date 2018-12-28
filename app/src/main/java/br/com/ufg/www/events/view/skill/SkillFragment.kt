package br.com.ufg.www.events.view.skill

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import br.com.redcode.base.utils.Alerts
import br.com.redcode.easyrestful.library.fragment.FragmentMVVM
import br.com.ufg.www.events.R
import br.com.ufg.www.events.data.model.Skill
import br.com.ufg.www.events.databinding.FragmentSkillBinding
import br.com.ufg.www.events.extensions.showOrHide
import com.google.android.material.chip.Chip
import com.google.android.material.snackbar.Snackbar
import java.util.*


class SkillFragment : FragmentMVVM<FragmentSkillBinding, SkillViewModel>() {

    override val classViewModel = SkillViewModel::class.java
    override val layout = R.layout.fragment_skill

    private val skills by lazy { arguments?.getParcelableArrayList<Skill>(PARAMETER) }

    companion object {
        const val PARAMETER = "skills"

        fun newInstance(skills: ArrayList<Skill>?): SkillFragment {
            val frag = SkillFragment()
            val args = Bundle()
            args.putParcelableArrayList(PARAMETER, skills)
            frag.arguments = args
            return frag
        }
    }

    override fun afterOnCreate() {
        super.afterOnCreate()
        viewModel.load(skills)
        binding.imageViewAdd.setOnClickListener { add() }
    }

    private fun add() {
        val unselecteds = viewModel.getUnselecteds()

        if (unselecteds.isNotEmpty()) {
            val options = unselecteds.map { it.description }

            val dialog = Alerts.getDialogSimpleList(
                    context = activity as Context,
                    options = options,
                    callback = DialogInterface.OnClickListener { dialog, which ->
                        dialog.dismiss()
                        val selected = viewModel.getUnselecteds()[which]
                        viewModel.add(selected)
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
            "onAdded" -> onAdded(obj)
            "onRemoved" -> if (obj != null && obj is Skill) onRemoved(skill = obj)
            "refreshVisibilityImageViewAdd" -> if (obj != null && obj is Boolean) refreshVisibilityImageViewAdd(obj)
            else -> super.handleEvent(event, obj)
        }
    }

    private fun onAdded(obj: Any?) {
        if (obj != null) {
            when (obj) {
                is Skill -> onAdded(skill = obj)
                is List<*> -> obj.forEach { onAdded(it as Skill) }
            }
        }
    }

    private fun onAdded(skill: Skill) {
        val viewChip = Chip(activity)
        viewChip.text = skill.description
        viewChip.tag = skill.id
        viewChip.isCheckable = false
        viewChip.isCloseIconEnabled = true
        viewChip.setOnCloseIconClickListener { viewModel.remove(skill) }

        binding.chipGroup.addView(viewChip)
    }

    private fun onRemoved(skill: Skill) {
        showVisualFeedback(skill)
        val viewChip = binding.chipGroup.findViewWithTag<Chip>(skill.id)
        binding.chipGroup.removeView(viewChip)
    }

    private fun showVisualFeedback(skill: Skill) {
        val message = "'${skill.description}' ${getString(R.string.removed)}"
        val snackbar = Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT)
        snackbar.setAction(getString(R.string.undo)) {
            viewModel.add(skill)
        }
        snackbar.show()
        viewModel.refreshVisibilityImageViewAdd()
    }

    private fun refreshVisibilityImageViewAdd(showOrHide: Boolean) = binding.imageViewAdd.showOrHide(showOrHide)

}