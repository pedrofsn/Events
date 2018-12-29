package br.com.ufg.www.events.view.skill

import br.com.redcode.easyrecyclerview.library.extension_functions.clearAndAddAll
import br.com.redcode.easyrestful.library.impl.viewmodel.BaseViewModel
import br.com.ufg.www.events.data.model.Skill
import java.util.*

class SkillViewModel : BaseViewModel() {

    val skills = arrayListOf<Skill>()

    fun load(skills: ArrayList<Skill>?) {
        this.skills.clearAndAddAll(skills)
        sendEventToUI("onAdded", getSelecteds())
    }

    fun getUnselecteds() = skills.filterNot { it.selected }
    fun getSelecteds() = skills.filter { it.selected }

    fun add(skill: Skill) {
        skills.filter { it.idSkill == skill.idSkill }.firstOrNull()?.selected = true
        sendEventToUI("onAdded", skill)
    }

    fun remove(skill: Skill) {
        skills.filter { it.idSkill == skill.idSkill }.firstOrNull()?.selected = false
        sendEventToUI("onRemoved", skill)
    }

    fun hasSelected() = skills.filter { it.selected }.isNotEmpty()
    fun refreshVisibilityImageViewAdd() = sendEventToUI("refreshVisibilityImageViewAdd", getUnselecteds().isNotEmpty())

}