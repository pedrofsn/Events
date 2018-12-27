package br.com.ufg.www.events.view.event.list

import android.view.View
import br.com.redcode.base.mvvm.extensions.observer
import br.com.redcode.easyrecyclerview.library.extension_functions.setCustomAdapter
import br.com.redcode.easyrestful.library.impl.activity.ActivityMVVM
import br.com.ufg.www.events.R
import br.com.ufg.www.events.data.model.Event
import br.com.ufg.www.events.data.ui.LabelEvents
import br.com.ufg.www.events.databinding.ActivityEventsBinding
import br.com.ufg.www.events.view.event.list.adapter.AdapterEvent

class EventsActivity : ActivityMVVM<ActivityEventsBinding, EventsViewModel>() {

    override val classViewModel = EventsViewModel::class.java
    override val layout = R.layout.activity_events

    private val adapter = AdapterEvent() { obj: Event, _: Int -> onClickItem(obj) }

    private val observer = observer<LabelEvents> { updateUI(it) }

    override fun afterOnCreate() = binding.recyclerView.setCustomAdapter(adapter)

    override fun setupUI() {
        super.setupUI()
        viewModel.liveData.observe(this, observer)
    }

    private fun updateUI(label: LabelEvents) = adapter.setCustomList(label.items)

    override fun onResume() {
        super.onResume()
        viewModel.load()
    }

    fun register(view: View?) = showSimpleAlert("not yet")

    private fun onClickItem(event: Event) {

    }

}
