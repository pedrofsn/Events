package br.com.ufg.www.events.view.event.list

import br.com.redcode.easyrestful.library.impl.viewmodel.BaseViewModelWithLiveData
import br.com.ufg.www.events.App
import br.com.ufg.www.events.data.offline.interactor.InteractorEvent
import br.com.ufg.www.events.data.ui.LabelEvents
import kotlinx.coroutines.launch

class EventsViewModel : BaseViewModelWithLiveData<LabelEvents>() {

    private val interactor = InteractorEvent()

    override fun load() {
        launch(coroutineContext) {
            val results = interactor.readAll(App.userLoggedIn?.id!!).await()
            liveData.postValue(LabelEvents(results))
        }
    }

}