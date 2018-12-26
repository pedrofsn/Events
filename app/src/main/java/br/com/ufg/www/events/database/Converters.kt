package br.com.ufg.www.events.database

import androidx.room.TypeConverter
import java.util.*

/**
 * Created by pedrofsn on 10/11/2017.
 */
class Converters {

    // Date <-> Long
    @TypeConverter
    fun fromDate(value: Long?): Date? {
        return if (value == null) null else Date(value)
    }

    @TypeConverter
    fun toDate(date: Date?): Long? {
        return date?.time
    }

}