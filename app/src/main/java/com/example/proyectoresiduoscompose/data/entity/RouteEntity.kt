package com.example.proyectoresiduoscompose.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "route",
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
data class RouteEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "route_id")
    val routeId: Int = 0,

    val name: String,
    val state: String,

    @ColumnInfo(name = "start_date")
    val startDate: Long,

    @ColumnInfo(name = "end_date")
    val endDate: Long?,

    @ColumnInfo(name = "truck_id")
    val truckId: Int?
)