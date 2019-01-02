package br.com.ufg.www.events.view.places.list

import android.content.Context
import android.content.DialogInterface
import androidx.work.*
import br.com.redcode.base.mvvm.extensions.observer
import br.com.redcode.base.utils.Alerts
import br.com.redcode.easyrecyclerview.library.extension_functions.setCustomAdapter
import br.com.redcode.easyrestful.library.fragment.FragmentSwipeRefreshRecyclerViewMVVM
import br.com.ufg.www.events.R
import br.com.ufg.www.events.architecture.SampleWorker
import br.com.ufg.www.events.architecture.SampleWorker.Companion.WORKMANAGER_USER_ID
import br.com.ufg.www.events.data.model.Place
import br.com.ufg.www.events.databinding.FragmentPlacesBinding
import br.com.ufg.www.events.view.places.list.adapter.AdapterPlace
import br.com.ufg.www.events.view.places.map.GoogleMapsActivity
import java.util.concurrent.TimeUnit

class PlacesFragment : FragmentSwipeRefreshRecyclerViewMVVM<FragmentPlacesBinding, PlacesViewModel>() {

    override val colummns = 1
    override val hasSearch = false
    override val hasSwipeRefreshLayout = false
    override val classViewModel = PlacesViewModel::class.java
    override val layout = R.layout.fragment_places

    override val adapter = AdapterPlace() { place: Place, _: Int -> openInMaps(place) }
    private val observer = observer<List<Place>> { updateUI(it) }

    override fun afterOnCreate() {
        binding.recyclerView.setCustomAdapter(adapter)
        binding.fab.setOnClickListener { addSample() }
    }

    override fun setupUI() {
        super.setupUI()
        viewModel.myList.observe(this, observer)
    }

    private fun updateUI(items: List<Place>) {
        adapter.setDiffList(items)
    }

    override fun onResume() {
        super.onResume()
        viewModel.load()
    }

    private fun addSample() {
        val options = listOf("Coroutines (background)", "WorkManager (foreground delayed 3 seconds)")
        val alert = Alerts.getDialogSimpleList(activity as Context, options, DialogInterface.OnClickListener { dialog, which ->
            dialog.dismiss()
            when (which) {
                0 -> addUsingCoroutines()
                1 -> addUsingWorkManager()
            }
        })
        alert.setTitle("Add sample address")
        alert.show()
    }

    private fun addUsingCoroutines() = viewModel.addSample()

    private fun addUsingWorkManager() {
        val workData = Data.Builder()
                .putLong(WORKMANAGER_USER_ID, viewModel.getIdUser())
                .build()

        val myConstraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build()

        val worker = OneTimeWorkRequest.Builder(SampleWorker::class.java)
                .setInputData(workData)
                .setConstraints(myConstraints)
                .setInitialDelay(3, TimeUnit.SECONDS)
                .build()

        WorkManager.getInstance().enqueue(worker)
    }

    private fun openInMaps(place: Place) = goTo<GoogleMapsActivity>("place" to place)

}
