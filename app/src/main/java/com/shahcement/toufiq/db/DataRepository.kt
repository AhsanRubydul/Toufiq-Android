package com.shahcement.toufiq.db

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
}