package br.com.pedrofsn.jobs.data.model

import android.os.Parcelable
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import kotlinx.parcelize.Parcelize

@Parcelize
data class JobItem(
    val id: Long,
    val title: String,
    val place: String,
    val date: String,
    val description: String
) : Parcelable {

    fun countDaysFromDateUntilNow(): Int {
        try {
            val now = getTodayInMilliseconds()
            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            val date = sdf.parse(date)
            if (date != null) {
                val diff = now - date.time
                val days = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS)
                return days.toInt()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return 0
    }

    private fun getTodayInMilliseconds() = Calendar.getInstance().run {
        set(Calendar.HOUR, 0)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
        return@run timeInMillis
    }
}