package br.com.ufg.www.events.view.event.jobs

import br.com.redcode.base.extensions.toLogcat
import br.com.redcode.easyrestful.library.impl.viewmodel.BaseViewModelWithLiveData
import br.com.ufg.www.events.App
import br.com.ufg.www.events.data.model.Event
import br.com.ufg.www.events.data.offline.interactor.InteractorEvent
import br.com.ufg.www.events.data.ui.LabelEvents
import kotlinx.coroutines.launch

class JobsViewModel : BaseViewModelWithLiveData<LabelEvents>() {
    // TODO FAKE DATA
    private val interactor = InteractorEvent()

    override fun load() = load(null)

    fun load(query: String?) {
        launch(coroutineContext) {
            val x = interactor.getAllEvents()
            "Capturou: ${x.size}".toLogcat()

            val results = interactor.readAll(App.userLoggedIn?.idUser!!)
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