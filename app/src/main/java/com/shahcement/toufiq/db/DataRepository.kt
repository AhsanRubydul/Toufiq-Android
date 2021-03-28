package com.shahcement.toufiq.db

import com.shahcement.toufiq.db.entity.District
import com.shahcement.toufiq.db.entity.Wakt

class DataRepository {

    private val mDatabase: AppDatabase by lazy { AppDatabase.getInstance() }

    companion object {
        private lateinit var INSTANCE: DataRepository

        fun getInstance(): DataRepository {
            if (!::INSTANCE.isInitialized) {
                synchronized(DataRepository::class) {
                    if (!::INSTANCE.isInitialized) {
                        INSTANCE = DataRepository()
                    }
                }
            }
            return INSTANCE
        }
    }

    fun getDistricts(): List<District> {
        return mDatabase.districtDao().getDistricts()
    }

    fun getWakt(districtId: Int, date: String): Wakt? {
        return mDatabase.waktDao().getWakt(districtId, date)
    }
}