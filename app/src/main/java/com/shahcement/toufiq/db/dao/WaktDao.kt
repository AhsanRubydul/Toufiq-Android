package com.shahcement.toufiq.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.shahcement.toufiq.db.entity.District
import com.shahcement.toufiq.db.entity.Wakt

@Dao
interface WaktDao {
    @Query("SELECT * FROM wakt_timing WHERE district_id = :districtId and timing_of_date = :date ORDER BY wakt_timing_id DESC LIMIT 1")
    fun getWakt(districtId: Int, date: String): Wakt?
}
