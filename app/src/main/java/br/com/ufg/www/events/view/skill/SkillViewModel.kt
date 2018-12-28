package br.com.ufg.www.events.view.skill

import br.com.redcode.easyrecyclerview.library.extension_functions.clearAndAddAll
import br.com.redcode.easyrestful.library.impl.viewmodel.BaseViewModel
import br.com.ufg.www.events.data.model.JobType
import br.com.ufg.www.events.data.offline.interactor.InteractorJobType
import br.com.ufg.www.events.extensions.isValid
import kotlinx.coroutines.launch

class SkillViewModel : BaseViewModel() {

    private val interactorJobTypes = InteractorJobType()

    private val jobTypes = arrayListOf<JobType>()

    fun load(idEvent: Long) {
        launch(coroutineContext) {
            val result = if (idEvent.isValid()) interactorJobTypes.readAll(idEvent) else interactorJobTypes.readAll()
            jobTypes.clearAndAddAll(result)

            sendEventToUI("addJobType", getSelectedJobTypes())
        }
    }

    fun getSelectableJobTypes() = jobTypes.filterNot { it.selected }
    fun getSelectedJobTypes() = jobTypes.filter { it.selected }

    fun addJobType(jobType: JobType) {
        jobTypes.filter { it.id == jobType.id }.firstOrNull()?.selected = true
        sendEventToUI("addJobType", jobType)
    }

    fun removeJobType(jobType: JobType) {
        jobTypes.filter { it.id == jobType.id }.firstOrNull()?.selected = false
        sendEventToUI("removeJobType", jobType)
    }

    fun hasSelectedJobType() = jobTypes.filter { it.selected }.isNotEmpty()
    fun refreshVisibilityImageViewAdd() = sendEventToUI("refreshVisibilityImageViewAdd", getSelectableJobTypes().isNotEmpty())

}