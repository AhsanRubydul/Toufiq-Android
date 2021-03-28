package com.shahcement.toufiq.data.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.shahcement.toufiq.data.db.entity.District

@Dao
interface DistrictDao {
    @Query("SELECT * FROM district")
    fun getDistricts(): List<District>
}
