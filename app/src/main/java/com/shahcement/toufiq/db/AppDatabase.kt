package com.shahcement.toufiq.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.shahcement.toufiq.app.App

@Database(entities = [District::class], version = 2)
abstract class AppDatabase : RoomDatabase() {

    abstract fun districtDao(): DistrictDao

    companion object {
        private const val DATABASE_NAME = "db.sqlite"

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(): AppDatabase {

            val tempInstance = INSTANCE

            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    App.instance.applicationContext,
                    AppDatabase::class.java, DATABASE_NAME)
                    .createFromAsset(DATABASE_NAME)
                    .allowMainThreadQueries()
                    .build()
                INSTANCE = instance
                return instance
            }
        }

    }
}

