package br.com.ufg.www.events.view.event.list

import br.com.redcode.base.mvvm.extensions.observer
import br.com.redcode.easyrecyclerview.library.extension_functions.setCustomAdapter
import br.com.redcode.easyrestful.library.fragment.FragmentMVVM
import br.com.ufg.www.events.R
import br.com.ufg.www.events.data.model.Event
import br.com.ufg.www.events.data.ui.LabelEvents
import br.com.ufg.www.events.databinding.FragmentEventsBinding
import br.com.ufg.www.events.view.event.list.adapter.AdapterEvent
import br.com.ufg.www.events.view.event.register.RegisterEventActivity

class FragmentEvents : FragmentMVVM<FragmentEventsBinding, EventsViewModel>() {

    override val classViewModel = EventsViewModel::class.java
    override val layout = R.layout.fragment_events

    private val adapter = AdapterEvent() { obj: Event, _: Int -> openEdit(obj) }

    private val observer = observer<LabelEvents> { updateUI(it) }

    override fun afterOnCreate() {
        binding.recyclerView.setCustomAdapter(adapter)
        binding.fab.setOnClickListener { openRegister() }
    }

    override fun setupUI() {
        super.setupUI()
        viewModel.liveData.observe(this, observer)
    }

    private fun updateUI(label: LabelEvents) = adapter.setCustomList(label.items)

    override fun onResume() {
        super.onResume()
        viewModel.load()
    }

    private fun openRegister() = goTo<RegisterEventActivity>()
    private fun openEdit(event: Event) = goTo<RegisterEventActivity>("id" to event.id)

}