package br.com.ufg.www.places.mvp.places.list

import android.os.Bundle
import br.com.ufg.www.places.R
import br.com.ufg.www.places.domain.BaseActivity
import br.com.ufg.www.places.domain.MyOnItemClickListener
import br.com.ufg.www.places.model.Place
import br.com.ufg.www.places.mvp.places.list.adapter.AdapterPlace
import kotlinx.android.synthetic.main.activity_places.*

class PlacesActivity : BaseActivity(), MyOnItemClickListener<Place>, Contract.View {

    private val presenter = Presenter(this)
    private val adapter = AdapterPlace(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_places)
        recyclerView.adapter = adapter
    }

    override fun load() = presenter.load()

    override fun openInMaps(place: Place) {

    }

    override fun onLoaded(places: List<Place>) = adapter.setCustomList(places)

    override fun onItemClickListener(item: Place, position: Int) = openInMaps(item)

}
