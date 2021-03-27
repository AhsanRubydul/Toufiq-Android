package com.shahcement.toufiq.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.shahcement.toufiq.db.entity.District

@Dao
interface DistrictDao {
    @Query("SELECT * FROM district")
    fun getDistricts(): List<District>
}
