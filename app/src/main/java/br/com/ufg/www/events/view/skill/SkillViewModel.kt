package br.com.ufg.www.events.view.skill

import br.com.redcode.easyrecyclerview.library.extension_functions.clearAndAddAll
import br.com.redcode.easyrestful.library.impl.viewmodel.BaseViewModel
import br.com.ufg.www.events.data.model.Skill
import br.com.ufg.www.events.data.offline.interactor.InteractorSkill
import br.com.ufg.www.events.extensions.isValid
import kotlinx.coroutines.launch

class SkillViewModel : BaseViewModel() {

    private val interactor = InteractorSkill()

    private val skills = arrayListOf<Skill>()

    fun load(idEvent: Long) {
        launch(coroutineContext) {
            val result = if (idEvent.isValid()) interactor.readAll(idEvent) else interactor.readAll()
            skills.clearAndAddAll(result)

            sendEventToUI("onAdded", getSelecteds())
        }
    }

    fun getUnselecteds() = skills.filterNot { it.selected }
    fun getSelecteds() = skills.filter { it.selected }

    fun add(skill: Skill) {
        skills.filter { it.id == skill.id }.firstOrNull()?.selected = true
        sendEventToUI("onAdded", skill)
    }

    fun remove(skill: Skill) {
        skills.filter { it.id == skill.id }.firstOrNull()?.selected = false
        sendEventToUI("onRemoved", skill)
    }

    fun hasSelected() = skills.filter { it.selected }.isNotEmpty()
    fun refreshVisibilityImageViewAdd() = sendEventToUI("refreshVisibilityImageViewAdd", getUnselecteds().isNotEmpty())

}