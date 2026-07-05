package com.ubaya.habittrackerleonida.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Habit::class, User::class], version = 1)
abstract class HabitDatabase : RoomDatabase() {
    abstract fun habitDao(): HabitDao

    companion object {
        @Volatile
        private var instance: HabitDatabase? = null
        private val LOCK = Any()

        private val roomCallback = object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                CoroutineScope(Dispatchers.IO).launch {
                    instance?.let { database ->
                        val defaultUser = User("student", "123")
                        database.habitDao().insertUser(defaultUser)
                    }
                }
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            HabitDatabase::class.java,
            "habitdb"
        )
            .addCallback(roomCallback)
            .build()

        operator fun invoke(context: Context) {
            if (instance != null) {
                synchronized(LOCK) {
                    instance ?: buildDatabase(context).also {
                        instance = it
                    }
                }
            }
        }

        fun getDatabase(context: Context): HabitDatabase {
            return instance ?: synchronized(this) {
                val _instance = buildDatabase(context)
                instance = _instance
                _instance
            }
        }

    }
}