package com.shahcement.tawfiq.data.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.shahcement.tawfiq.data.db.entity.Ramadan

@Dao
interface RamadanDao {
    @Query("SELECT * FROM sehri_iftari_timing WHERE district_id = :districtId and timing_of_date = :date ORDER BY sehri_iftar_timing_id DESC LIMIT 1")
    fun getRamadanTime(districtId: Int, date: String): Ramadan?

    @Query("SELECT * FROM sehri_iftari_timing WHERE district_id = :districtId")
    fun getAllRamadanTime(districtId: Int): List<Ramadan>
}
