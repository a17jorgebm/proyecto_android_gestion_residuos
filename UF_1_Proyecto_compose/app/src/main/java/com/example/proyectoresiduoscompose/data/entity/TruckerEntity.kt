package com.example.proyectoresiduoscompose.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "trucker",
    foreignKeys = [
        ForeignKey(
            entity = TruckEntity::class,
            parentColumns = ["truck_id"],
            childColumns = ["truck_id"],
            onDelete = ForeignKey.SET_NULL
        )
    ],
    indices = [Index("truck_id")]
)
data class TruckerEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "trucker_id")
    val truckerId: Int = 0,

    val dni: String,
    val name: String,

    @ColumnInfo(name = "last_name_1")
    val lastName1: String,

    @ColumnInfo(name = "last_name_2")
    val lastName2: String,

    val active: Boolean,

    @ColumnInfo(name = "truck_id")
    val truckId: Int?
)