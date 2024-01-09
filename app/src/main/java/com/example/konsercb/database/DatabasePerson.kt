package com.example.konsercb.database

import android.content.Context
import androidx.databinding.adapters.Converters
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [Person::class], version = 1, exportSchema = false)
abstract class DatabasePerson : RoomDatabase() {

    abstract fun PersonDAO(): PersonDAO

    companion object {
        @Volatile
        private var instance: DatabasePerson? = null

        fun getDatabase(context: Context): DatabasePerson {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): DatabasePerson {
            return Room.databaseBuilder(
                context.applicationContext,
                DatabasePerson::class.java,
                "person_database"
            )
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}
