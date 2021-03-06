package br.com.pedrofsn.jobs.data.model

import android.os.Parcelable
import br.com.pedrofsn.jobs.data.model.DateCalculator.asDate
import br.com.pedrofsn.jobs.data.model.DateCalculator.countDaysBetween
import java.util.*
import kotlinx.parcelize.Parcelize

@Parcelize
data class JobItem(
    override val id: Long,
    val title: String,
    val place: String,
    val date: String,
    val description: String
) : Parcelable, WithID {

    fun countDaysFromDateUntilNow(): Int {
        val today = getTodayAsDate()

        try {
            val parsed = date.asDate()
            if (parsed != null) {
                return countDaysBetween(start = today, end = parsed)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return -1
    }

    private fun getTodayAsDate() = Calendar.getInstance().run {
        set(Calendar.HOUR, 0)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
        return@run time
    }
}