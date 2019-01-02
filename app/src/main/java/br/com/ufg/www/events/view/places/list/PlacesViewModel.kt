package br.com.ufg.www.events.view.places.list

import androidx.lifecycle.MediatorLiveData
import br.com.redcode.base.extensions.to__yyyy_MM_dd__HH_mm_ss
import br.com.redcode.base.utils.Constants.INVALID_VALUE
import br.com.redcode.easyrestful.library.impl.viewmodel.BaseViewModelWithLiveData
import br.com.ufg.www.events.App
import br.com.ufg.www.events.data.model.Place
import br.com.ufg.www.events.data.offline.entities.PlaceEntity
import br.com.ufg.www.events.data.offline.interactor.InteractorPlace
import br.com.ufg.www.events.data.ui.LabelPlaces
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.util.*

class PlacesViewModel : BaseViewModelWithLiveData<LabelPlaces>() {

    private val interactor = InteractorPlace()
    val myList = MediatorLiveData<List<Place>>()

    private val random = Random()

    override fun load() {
        launch(main()) {
            val places = async(io()) { interactor.readAll(getIdUser()) }
            myList.addSource(places.await(), myList::setValue)
        }
    }

    fun addSample() {
        launch(coroutineContext) {
            val entity = PlaceEntity(
                    idUser = getIdUser(),
                    latitude = random.nextDouble().toString(),
                    longitude = random.nextDouble().toString(),
                    address = "Coroutines\n--> ${Calendar.getInstance().to__yyyy_MM_dd__HH_mm_ss()}"
            )
            interactor.save(entity)
        }
    }

    fun getIdUser() = App.userLoggedIn?.idUser ?: INVALID_VALUE.toLong()
}