package br.com.ufg.www.events.architecture

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import br.com.redcode.base.extensions.to__yyyy_MM_dd__HH_mm_ss
import br.com.redcode.base.utils.Constants.INVALID_VALUE
import br.com.ufg.www.events.data.offline.entities.PlaceEntity
import br.com.ufg.www.events.data.offline.interactor.InteractorPlace
import java.util.*


class SampleWorker(context: Context, workerParameters: WorkerParameters) : Worker(context, workerParameters) {

    companion object {
        const val WORKMANAGER_USER_ID = "WORKMANAGER_USER_ID"
    }

    private val interactor = InteractorPlace()
    private val random = Random()

    val id by lazy { inputData.getLong(WORKMANAGER_USER_ID, INVALID_VALUE.toLong()) }

    override fun doWork(): Result {
        val entity = PlaceEntity(
                idUser = id,
                latitude = random.nextDouble().toString(),
                longitude = random.nextDouble().toString(),
                address = "WorkManager\n--> ${Calendar.getInstance().to__yyyy_MM_dd__HH_mm_ss()}"
        )
        interactor.saveByWorkManager(entity)

        return Result.success()
    }
}