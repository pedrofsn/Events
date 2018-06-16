package br.com.ufg.www.events.mvp.maps.list

import android.os.Bundle
import br.com.ufg.www.events.R
import br.com.ufg.www.events.domain.BaseActivity
import br.com.ufg.www.events.domain.MyOnItemClickListener
import br.com.ufg.www.events.model.Event
import br.com.ufg.www.events.mvp.maps.list.adapter.AdapterEvent
import kotlinx.android.synthetic.main.activity_list_places_.*

class List_Places_Activity : BaseActivity(), MyOnItemClickListener<Event>, Contract.View {

    private val presenter = Presenter(this)
    private val adapter = AdapterEvent(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_places_)
        recyclerViewEvents.adapter = adapter
    }

    override fun load() = presenter.load()

    override fun openInMaps(event: Event) {

    }

    override fun onLoaded(events: List<Event>) = adapter.setCustomList(events)

    override fun onItemClickListener(item: Event, position: Int) = openInMaps(item)

}
