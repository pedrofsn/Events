package br.com.ufg.www.events.view.event

import br.com.redcode.easyrecyclerview.library.extension_functions.clearAndAddAll
import br.com.redcode.easyrestful.library.impl.viewmodel.BaseViewModelWithLiveData
import br.com.ufg.www.events.model.JobType
import br.com.ufg.www.events.model.ui.InputEvent
import kotlinx.coroutines.launch

class EventRegisterViewModel : BaseViewModelWithLiveData<InputEvent>() {

    private val interactorJobTypes = InteractorJobTypes()
    private val jobTypes = arrayListOf<JobType>()
    val selectedJobTypes = arrayListOf<JobType>()

    override fun load() {
        liveData.postValue(InputEvent())

        launch(coroutineContext) {
            jobTypes.clearAndAddAll(interactorJobTypes.getJobTypes().await())
        }
    }

    fun getSelectableJobTypes() = jobTypes.filterNot { it in selectedJobTypes }

    fun addJobType(jobType: JobType) {
        selectedJobTypes.add(jobType)
        sendEventToUI("addJobType", jobType)
    }

    fun removeJobType(jobType: JobType) {
        selectedJobTypes.remove(jobType)
        sendEventToUI("removeJobType", jobType)
    }

    fun hasSelectedJobType() = selectedJobTypes.isNotEmpty()
    fun refreshVisibilityImageViewAdd() = sendEventToUI("refreshVisibilityImageViewAdd", getSelectableJobTypes().isNotEmpty())

    fun save() {

    }

}