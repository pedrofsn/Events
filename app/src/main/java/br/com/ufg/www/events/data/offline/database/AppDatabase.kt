package br.com.ufg.www.events.data.offline.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import br.com.ufg.www.events.App
import br.com.ufg.www.events.data.offline.dao.*
import br.com.ufg.www.events.data.offline.entities.*
import java.util.concurrent.Executors

/**
 * Created by pedrofsn on 28/05/2018.
 */

@Database(
        entities = [
            UserEntity::class,
            PlaceEntity::class,
            JobTypeEntity::class,
            EventEntity::class,
            EventWithJobTypesEntity::class
        ],
        version = 4,
        exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDAO(): UserDao
    abstract fun placeDAO(): PlaceDao
    abstract fun eventDAO(): EventDAO
    abstract fun jobTypesDAO(): JobTypeDao
    abstract fun eventWithJobTypesDAO(): EventWithJobTypesDAO

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null
        private val DATABASE_NAME: String = "events.db"

        fun getInstance(context: Context = App.getContext()!!) = INSTANCE
                ?: synchronized(this) {
                    INSTANCE
                            ?: buildDatabase(context).also { INSTANCE = it }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            val room = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, DATABASE_NAME)
            room.fallbackToDestructiveMigration()
            seedDatabase(room)
            return room.build()
        }

        private fun seedDatabase(room: Builder<AppDatabase>) {
            room.addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    Executors.newSingleThreadExecutor().execute { Mock.seedDatabase() }
                }
            })
        }
    }
}