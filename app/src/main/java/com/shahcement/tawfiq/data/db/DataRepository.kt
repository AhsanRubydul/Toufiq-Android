package com.shahcement.tawfiq.data.db

import com.shahcement.tawfiq.data.db.entity.District
import com.shahcement.tawfiq.data.db.entity.Ramadan
import com.shahcement.tawfiq.data.db.entity.Wakt

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

    fun getRamadanTime(districtId: Int, date: String): Ramadan? {
        return mDatabase.ramadanDao().getRamadanTime(districtId, date)
    }

    fun getAllRamadanTime(districtId: Int): List<Ramadan> {
        return mDatabase.ramadanDao().getAllRamadanTime(districtId)
    }
}