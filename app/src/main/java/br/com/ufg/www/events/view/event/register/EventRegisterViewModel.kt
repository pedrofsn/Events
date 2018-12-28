package br.com.ufg.www.events.view.event.register

import br.com.redcode.base.extensions.extract
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
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class EventRegisterViewModel : BaseViewModelWithLiveData<InputEvent>() {

    private val interactorJobTypes = InteractorJobType()
    private val interactorPlaces = InteractorPlace()
    private val interactorEvent = InteractorEvent()
    private val interactorEventWithJobTypes = InteractorEventWithJobType()

    private val jobTypes = arrayListOf<JobType>()

    override fun load() {
        launch(coroutineContext) {
            val loadLabel = async {
                val result = if (id.isValid()) interactorJobTypes.readAll(idEvent = id) else interactorJobTypes.readAll()
                jobTypes.clearAndAddAll(result)

                val label = InputEvent()

                if (id.isValid()) {

                    interactorEvent.read(idEvent = id)?.let { event ->
                        label.idEvent = event.id
                        label.name = event.name
                        label.date = event.getDateFormmated()

                        interactorPlaces.read(event.idPlace)?.let { place ->
                            label.idPlace = place.id
                            label.address = extract safe place.address
                            label.latitude = place.latitude
                            label.longitude = place.longitude
                        }
                    }
                }

                label
            }

            liveData.postValue(loadLabel.await())
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

    fun save() {
        launch(coroutineContext) {
            try {
                liveData.value?.apply {
                    App.userLoggedIn?.id?.let { idUser ->
                        val placeEntity = toPlaceEntity(idUser)

                        val idPlace = interactorPlaces.save(placeEntity)

                        if (idPlace.isValid()) {
                            val eventEntity = toEventEntity(idUser, idPlace)

                            val idEvent = interactorEvent.save(eventEntity)

                            if (idEvent.isValid()) {
                                val toInsert = getSelectedJobTypes().map { it.toEventWithJobTypesEntity(idEvent) }.toTypedArray()
                                interactorEventWithJobTypes.insertAll(idEvent, *toInsert)
                            }

                            sendEventToUI("onRegistered")
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