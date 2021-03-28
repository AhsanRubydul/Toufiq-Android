package com.shahcement.tawfiq.data.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.shahcement.tawfiq.data.db.entity.Wakt

@Dao
interface WaktDao {
    @Query("SELECT * FROM wakt_timing WHERE district_id = :districtId and timing_of_date = :date ORDER BY wakt_timing_id DESC LIMIT 1")
    fun getWakt(districtId: Int, date: String): Wakt?
}
