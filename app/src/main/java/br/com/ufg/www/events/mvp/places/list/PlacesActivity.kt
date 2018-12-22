package br.com.ufg.www.events.mvp.places.list

import android.content.Intent
import android.os.Bundle
import android.view.View
import br.com.ufg.www.events.R
import br.com.ufg.www.events.domain.BaseActivity
import br.com.ufg.www.events.model.Place
import br.com.ufg.www.events.mvp.places.list.adapter.AdapterPlace
import br.com.ufg.www.events.mvp.places.map.GoogleMapsActivity
import br.com.ufg.www.events.mvp.places.register.RegisterPlaceActivity
import kotlinx.android.synthetic.main.activity_places.*

class PlacesActivity : BaseActivity(), View.OnClickListener, Contract.View {

    private val presenter = Presenter(this)
    private val adapter = AdapterPlace() { place: Place, _: Int -> openInMaps(place) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_places)
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        recyclerView.adapter = adapter
        fab.setOnClickListener(this)
    }

    override fun onResume() {
        super.onResume()
        load()
    }

    override fun load() = presenter.load()

    override fun onClick(v: View?) {
        v?.let {
            when (v.id) {
                fab.id -> register()
            }
        }
    }

    override fun onLoaded(places: List<Place>) = adapter.setCustomList(places)

    override fun register() = startActivity(Intent(this@PlacesActivity, RegisterPlaceActivity::class.java))

    override fun openInMaps(place: Place) {
        val intent = Intent(this, GoogleMapsActivity::class.java)
        intent.putExtra("place", place)
        startActivity(intent)
    }

}
