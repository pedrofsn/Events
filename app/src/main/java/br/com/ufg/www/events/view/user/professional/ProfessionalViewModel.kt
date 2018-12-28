package br.com.ufg.www.events.view.user.professional

import br.com.redcode.easyrecyclerview.library.extension_functions.clearAndAddAll
import br.com.redcode.easyrestful.library.impl.viewmodel.BaseViewModel
import br.com.ufg.www.events.data.model.Skill
import br.com.ufg.www.events.data.offline.interactor.InteractorSkill
import kotlinx.coroutines.launch

class ProfessionalViewModel : BaseViewModel() {

    private val interactorSkills = InteractorSkill()
    val skills = arrayListOf<Skill>()

    fun load() {
        launch(coroutineContext) {
            val results = interactorSkills.getMySkills()
            skills.clearAndAddAll(results)
        }
    }

}