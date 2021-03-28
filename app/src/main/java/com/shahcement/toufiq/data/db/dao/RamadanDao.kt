package com.shahcement.toufiq.data.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.shahcement.toufiq.data.db.entity.Ramadan

@Dao
interface RamadanDao {
    @Query("SELECT * FROM sehri_iftar_timing WHERE district_id = :districtId and timing_of_date = :date ORDER BY sehri_iftar_timing_id DESC LIMIT 1")
    fun getRamadanTime(districtId: Int, date: String): Ramadan?
}
