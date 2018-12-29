package br.com.ufg.www.events.view.event.register

import br.com.redcode.base.extensions.extract
import br.com.redcode.easyrecyclerview.library.extension_functions.clearAndAddAll
import br.com.redcode.easyrestful.library.impl.viewmodel.BaseViewModelWithLiveData
import br.com.ufg.www.events.App
import br.com.ufg.www.events.R
import br.com.ufg.www.events.data.model.Skill
import br.com.ufg.www.events.data.offline.interactor.InteractorEvent
import br.com.ufg.www.events.data.offline.interactor.InteractorEventWithSkills
import br.com.ufg.www.events.data.offline.interactor.InteractorPlace
import br.com.ufg.www.events.data.offline.interactor.InteractorSkill
import br.com.ufg.www.events.data.ui.InputEvent
import br.com.ufg.www.events.extensions.isValid
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class RegisterEventViewModel : BaseViewModelWithLiveData<InputEvent>() {

    private val interactorSkills = InteractorSkill()
    private val interactorPlaces = InteractorPlace()
    private val interactorEvent = InteractorEvent()
    private val interactorEventWithSkills = InteractorEventWithSkills()

    override fun load() {
        launch(coroutineContext) {
            val loadLabel = async {

                val label = InputEvent()

                if (id.isValid()) {

                    interactorEvent.read(idEvent = id)?.let { event ->
                        label.idEvent = event.idEvent
                        label.name = event.name
                        label.dateStart = event.getDateStartFormmated()
                        label.dateEnd = event.getDateEndFormmated()

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

    fun loadSkills(callback: (ArrayList<Skill>) -> Unit) {
        launch(coroutineContext) {
            val skills = arrayListOf<Skill>()
            val results = if (id.isValid()) interactorSkills.getSkillsSelectedsAndUnselecteds(idEvent = id) else interactorSkills.getAllSkills()
            skills.clearAndAddAll(results)
            callback.invoke(skills)
        }
    }

    fun save(selecteds: List<Skill>) {
        launch(coroutineContext) {
            try {
                liveData.value?.apply {
                    App.userLoggedIn?.idUser?.let { idUser ->
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