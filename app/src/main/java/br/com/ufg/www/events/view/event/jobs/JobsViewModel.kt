package br.com.ufg.www.events.view.event.jobs

import br.com.redcode.easyrestful.library.impl.viewmodel.BaseViewModelWithLiveData
import br.com.ufg.www.events.App
import br.com.ufg.www.events.data.model.Event
import br.com.ufg.www.events.data.offline.interactor.InteractorEvent
import br.com.ufg.www.events.data.ui.LabelEvents
import kotlinx.coroutines.launch

class JobsViewModel : BaseViewModelWithLiveData<LabelEvents>() {
    // TODO copied from EventsViewModel
    private val interactor = InteractorEvent()

    override fun load() = load(null)

    fun load(query: String?) {
        launch(coroutineContext) {
            val results = interactor.readAll(App.userLoggedIn?.id!!)
            val temp = arrayListOf<Event>()
            temp.addAll(results)
            temp.addAll(results)
            temp.addAll(results)
            temp.addAll(results)

            val result = if (query.isNullOrBlank().not()) {
                temp.filter { it.name.contains(query!!, true) }
            } else {
                temp
            }
            liveData.postValue(LabelEvents(result))

            showProgressbar(false)
        }
    }

}