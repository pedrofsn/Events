package br.com.ufg.www.events.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import br.com.ufg.www.events.App
import br.com.ufg.www.events.database.dao.UserDao
import br.com.ufg.www.events.database.entities.UserEntity

/**
 * Created by pedrofsn on 28/05/2018.
 */

@Database(entities = arrayOf(
        UserEntity::class
),
        version = 1,
        exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDAO(): UserDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null
        private val DATABASE_NAME: String = "events.db"

        fun getInstance(context : Context = App.instance) = INSTANCE ?: synchronized(this) {
            INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
        }

        private fun buildDatabase(context : Context): AppDatabase {
            val room = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, DATABASE_NAME)
//            room.addMigrations(object : Migration(1, 2) {
//                override fun migrate(database: SupportSQLiteDatabase) {
//                    database.execSQL("DROP TABLE tb_X;");
//                }
//            })
            return room.build()
        }
    }
}