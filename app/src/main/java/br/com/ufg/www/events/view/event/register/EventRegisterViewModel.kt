package br.com.ufg.www.events.view.event.register

import br.com.redcode.easyrecyclerview.library.extension_functions.clearAndAddAll
import br.com.redcode.easyrestful.library.impl.viewmodel.BaseViewModelWithLiveData
import br.com.ufg.www.events.App
import br.com.ufg.www.events.R
import br.com.ufg.www.events.data.model.JobType
import br.com.ufg.www.events.data.offline.interactor.InteractorEvent
import br.com.ufg.www.events.data.offline.interactor.InteractorEventWithJobType
import br.com.ufg.www.events.data.offline.interactor.InteractorJobType
import br.com.ufg.www.events.data.offline.interactor.InteractorPlace
import br.com.ufg.www.events.data.ui.InputEvent
import br.com.ufg.www.events.extensions.isValid
import kotlinx.coroutines.launch

class EventRegisterViewModel : BaseViewModelWithLiveData<InputEvent>() {

    private val interactorJobTypes = InteractorJobType()
    private val interactorPlaces = InteractorPlace()
    private val interactorEvent = InteractorEvent()
    private val interactorEventWithJobTypes = InteractorEventWithJobType()

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
        launch(coroutineContext) {
            try {
                liveData.value?.apply {
                    App.userLoggedIn?.id?.let { idUser ->
                        val placeEntity = toPlaceEntity(idUser)

                        val idPlace = interactorPlaces.save(placeEntity).await()

                        if (idPlace.isValid()) {
                            val eventEntity = toEventEntity(idUser, idPlace)

                            val idEvent = interactorEvent.save(eventEntity).await()

                            val needs = arrayListOf<Long>()
                            if (idEvent.isValid()) {
                                selectedJobTypes.forEach {
                                    val eventWithJobTypesEntity = it.toEventWithJobTypesEntity(idEvent)
                                    val idEventWithJobType = interactorEventWithJobTypes.save(eventWithJobTypesEntity).await()
                                    needs.add(idEventWithJobType)
                                }
                            }

                            if (needs.isNotEmpty()) {
                                sendEventToUI("onRegistered")
                            }
                        }
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                showSimpleAlert(App.getContext()?.getString(R.string.erro)!!)
            }
        }
    }

}