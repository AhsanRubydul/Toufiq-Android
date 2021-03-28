package com.shahcement.tawfiq.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "district")
data class District(
    @PrimaryKey(autoGenerate = false)
    val district_id: Int,
    @ColumnInfo(name = "name")
    val name: String) {

    override fun toString(): String {
        return name
    }
}
