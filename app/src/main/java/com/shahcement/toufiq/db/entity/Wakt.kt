package com.shahcement.toufiq.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "wakt_timing")
data class Wakt(
    @PrimaryKey(autoGenerate = false)
    val wakt_timing_id: Int,
    @ColumnInfo(name = "district_id")
    val districtId: Int,
    @ColumnInfo(name = "timing_of_date")
    val date: String,
    @ColumnInfo(name = "fazar_time")
    val fazarTime: String,
    @ColumnInfo(name = "zohar_time")
    val zoharTime: String,
    @ColumnInfo(name = "asar_time")
    val asarTime: String,
    @ColumnInfo(name = "maghrib_time")
    val maghribTime: String,
    @ColumnInfo(name = "isha_time")
    val ishaTime: String,
)
