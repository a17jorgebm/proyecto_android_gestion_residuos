package com.example.proyectoresiduoscompose.data.entity.model

import androidx.room.Embedded
import androidx.room.Relation
import com.example.proyectoresiduoscompose.data.entity.RouteEntity
import com.example.proyectoresiduoscompose.data.entity.TruckEntity

data class TruckWithRoutes(
    @Embedded val truck: TruckEntity,

    @Relation(
        parentColumn = "truck_id",
        entityColumn = "truck_id"
    )
    val routes: List<RouteEntity>
)