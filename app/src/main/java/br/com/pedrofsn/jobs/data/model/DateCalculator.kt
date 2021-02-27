package br.com.pedrofsn.jobs.data.model

import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

object DateCalculator {

    fun String.asDate(): Date? {
        val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        return sdf.parse(this)
    }

    fun countDaysBetween(start: Long, end: Long): Int {
        val diff = start - end
        val days = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS)
        return days.toInt()
    }

    fun countDaysBetween(start: Date, end: Date): Int {
        return countDaysBetween(start.time, end.time)
    }
}