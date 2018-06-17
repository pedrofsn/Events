package br.com.ufg.www.events.database

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.migration.Migration
import android.content.Context
import br.com.ufg.www.events.App
import br.com.ufg.www.events.database.dao.EventDao
import br.com.ufg.www.events.database.dao.UserDao
import br.com.ufg.www.events.database.entities.EventEntity
import br.com.ufg.www.events.database.entities.UserEntity

/**
 * Created by pedrofsn on 28/05/2018.
 */

@Database(entities = arrayOf(
        UserEntity::class,
        EventEntity::class
),
        version = 2,
        exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDAO(): UserDao
    abstract fun eventDAO(): EventDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null
        private val DATABASE_NAME: String = "events.db"

        fun getInstance(context: Context = App.instance) = INSTANCE ?: synchronized(this) {
            INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            val room = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, DATABASE_NAME)
            room.addMigrations(object : Migration(1, 2) {
                override fun migrate(database: SupportSQLiteDatabase) {
                    database.execSQL("CREATE TABLE events (latitude REAL not null,longitude REAL not null,description TEXT,login TEXT NOT NULL)");
                }
            })
            return room.build()
        }
    }
}