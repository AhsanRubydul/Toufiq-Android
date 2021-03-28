package com.shahcement.tawfiq.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(
    tableName = "sehri_iftari_timing",
    foreignKeys = [ForeignKey(
        entity = District::class,
        parentColumns = arrayOf("district_id"),
        childColumns = arrayOf("district_id"),
        onDelete = ForeignKey.NO_ACTION,
        onUpdate = ForeignKey.NO_ACTION
    )]
)
data class Ramadan(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "sehri_iftar_timing_id")
    val id: Int,
    @ColumnInfo(name = "district_id")
    val districtId: Int,
    @ColumnInfo(name = "timing_of_date")
    val date: String,
    @ColumnInfo(name = "sehri_time")
    val sahriTime: String,
    @ColumnInfo(name = "iftari_time")
    val iftarTime: String
)
