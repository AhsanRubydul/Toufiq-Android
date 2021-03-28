package com.shahcement.tawfiq.data.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.shahcement.tawfiq.data.db.entity.District

@Dao
interface DistrictDao {
    @Query("SELECT * FROM district")
    fun getDistricts(): List<District>
}
