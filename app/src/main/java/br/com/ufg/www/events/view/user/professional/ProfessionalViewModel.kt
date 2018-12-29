package br.com.ufg.www.events.view.user.professional

import br.com.redcode.easyrecyclerview.library.extension_functions.clearAndAddAll
import br.com.redcode.easyrestful.library.impl.viewmodel.BaseViewModel
import br.com.ufg.www.events.data.model.Skill
import br.com.ufg.www.events.data.offline.interactor.InteractorMySkills
import br.com.ufg.www.events.data.offline.interactor.InteractorSkill
import kotlinx.coroutines.launch

class ProfessionalViewModel : BaseViewModel() {

    private val interactorSkills = InteractorSkill()
    private val interactorMySkills = InteractorMySkills()

    val skills = arrayListOf<Skill>()

    fun load() {
        launch(coroutineContext) {
            val results = interactorSkills.getMySkills()
            skills.clearAndAddAll(results)
        }
    }

    fun saveMySkill(skill: Skill) = launch(coroutineContext) { interactorMySkills.save(skill.idSkill) }
    fun removeMySkill(skill: Skill) = launch(coroutineContext) { interactorMySkills.delete(skill.idSkill) }

}