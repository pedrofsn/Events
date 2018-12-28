package br.com.ufg.www.events.view.event.register

import br.com.redcode.base.extensions.extract
import br.com.redcode.easyrestful.library.impl.viewmodel.BaseViewModelWithLiveData
import br.com.ufg.www.events.App
import br.com.ufg.www.events.R
import br.com.ufg.www.events.data.model.Skill
import br.com.ufg.www.events.data.offline.interactor.InteractorEvent
import br.com.ufg.www.events.data.offline.interactor.InteractorEventWithSkills
import br.com.ufg.www.events.data.offline.interactor.InteractorPlace
import br.com.ufg.www.events.data.ui.InputEvent
import br.com.ufg.www.events.extensions.isValid
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class RegisterEventViewModel : BaseViewModelWithLiveData<InputEvent>() {

    private val interactorPlaces = InteractorPlace()
    private val interactorEvent = InteractorEvent()
    private val interactorEventWithSkills = InteractorEventWithSkills()

    override fun load() {
        launch(coroutineContext) {
            val loadLabel = async {
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

    fun save(selecteds: List<Skill>) {
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
                                val toInsert = selecteds.map { it.toEntity(idEvent) }.toTypedArray()
                                interactorEventWithSkills.insertAll(idEvent, *toInsert)
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