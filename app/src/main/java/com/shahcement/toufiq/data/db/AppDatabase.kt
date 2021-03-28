package com.shahcement.toufiq.data.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.shahcement.toufiq.app.App
import com.shahcement.toufiq.data.db.dao.DistrictDao
import com.shahcement.toufiq.data.db.dao.RamadanDao
import com.shahcement.toufiq.data.db.dao.WaktDao
import com.shahcement.toufiq.data.db.entity.District
import com.shahcement.toufiq.data.db.entity.Ramadan
import com.shahcement.toufiq.data.db.entity.Wakt

@Database(entities = [District::class, Wakt::class, Ramadan::class], version = 2)
abstract class AppDatabase : RoomDatabase() {

    abstract fun districtDao(): DistrictDao

    abstract fun waktDao(): WaktDao

    abstract fun ramadanDao(): RamadanDao

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
                    AppDatabase::class.java, DATABASE_NAME
                )
                    .createFromAsset(DATABASE_NAME)
                    .allowMainThreadQueries()
                    .build()
                INSTANCE = instance
                return instance
            }
        }

    }
}

