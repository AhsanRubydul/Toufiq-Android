package com.shahcement.toufiq.db

import androidx.room.Dao
import androidx.room.Query

@Dao
interface DistrictDao {
    @Query("SELECT * FROM district")
    fun getDistricts(): List<District>
}
