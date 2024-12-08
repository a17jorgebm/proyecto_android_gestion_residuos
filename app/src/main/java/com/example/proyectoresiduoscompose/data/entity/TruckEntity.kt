package com.example.proyectoresiduoscompose.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "truck")
data class TruckEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "truck_id")
    val truckId: Int = 0,

    val plate: String,
    val name: String
)