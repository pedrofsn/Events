package br.com.ufg.www.events.view.places.add_map

import android.content.Intent
import android.location.Location
import android.view.View
import br.com.redcode.base.extensions.setSubtituloToolbar
import br.com.ufg.www.events.R
import br.com.ufg.www.events.data.model.Place
import br.com.ufg.www.events.extensions.toLatLng
import kotlinx.android.synthetic.main.activity_maps_select_location.*

/**
 * Created by pedrofsn on 04/05/18.
 */
class MapsSelectLocationActivity : BaseMapLocationSearchActivity(), View.OnClickListener, Contract.View {

    override val layout: Int = R.layout.activity_maps_select_location
    private var firstRun = true

    override val onLocationChange: (Location) -> Unit = { location: Location ->
        presenter.stringLocation = "${location.latitude},${location.longitude}"
        setupInitialZoom(location)
    }

    private fun setupInitialZoom(location: Location) {
        if (firstRun) {
            setPosition(location.toLatLng(), ZOOM_IN)
            firstRun = false
        }
    }

    private val presenter = Presenter(this)

    override fun afterOnCreate() {
        super.afterOnCreate()
        enableHomeAsUpActionBar()
        buttonSave.setOnClickListener(this)
    }

    override fun onResume() {
        super.onResume()
        load()
    }

    private fun load() = askForLocation()

    override fun onClick(v: View?) {
        v?.let {
            when (v.id) {
                R.id.buttonSave -> save()
            }
        }
    }

    override fun search(query: String?) {
        super.search(query)
        presenter.load(query)
    }

    override fun updateUI(list: List<Place>) {
        if (list.isEmpty()) {
            showMessage(getString(R.string.results_not_found))
        } else {
            val positions = list.map { obj -> obj.toPosition() }
            seeMarkersOnCamera(positions)
        }
    }

    override fun onAdressStringUpdated(addressString: String) {
        super.onAdressStringUpdated(addressString)
        setSubtituloToolbar(addressString)
    }

    override fun save() {
        val intent = Intent()
        intent.putExtra("latLng", latLng)
        intent.putExtra("addressString", addressString)
        setResult(RESULT_OK, intent)
        finish()
    }
}

