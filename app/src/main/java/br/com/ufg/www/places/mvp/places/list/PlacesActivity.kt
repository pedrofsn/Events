package br.com.ufg.www.places.mvp.places.list

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import br.com.ufg.www.places.R
import br.com.ufg.www.places.domain.BaseActivity
import br.com.ufg.www.places.domain.MyOnItemClickListener
import br.com.ufg.www.places.model.Place
import br.com.ufg.www.places.mvp.places.LocationActivity
import br.com.ufg.www.places.mvp.places.list.adapter.AdapterPlace
import br.com.ufg.www.places.mvp.places.register.RegisterPlaceActivity
import kotlinx.android.synthetic.main.activity_places.*

class PlacesActivity : BaseActivity(), MyOnItemClickListener<Place>, Contract.View {

    private val presenter = Presenter(this)
    private val adapter = AdapterPlace(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_places)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        fab.setOnClickListener { startActivity(Intent(this@PlacesActivity, RegisterPlaceActivity::class.java)) }
    }

    override fun onResume() {
        super.onResume()
        load()
    }

    override fun load() = presenter.load()

    override fun openInMaps(place: Place) {
        val intent = Intent(this, LocationActivity::class.java)
        intent.putExtra("place", place)
        startActivity(intent)
    }

    override fun onLoaded(places: List<Place>) = adapter.setCustomList(places)

    override fun onItemClickListener(item: Place, position: Int) = openInMaps(item)

}
