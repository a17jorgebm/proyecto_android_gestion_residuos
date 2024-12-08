package com.example.proyectoresiduoscompose.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "route_collection",
    foreignKeys = [
        ForeignKey(
            entity = ClientEntity::class,
            parentColumns = ["client_id"],
            childColumns = ["client_id"],
            onDelete = ForeignKey.RESTRICT
        ),
        ForeignKey(
            entity = RouteEntity::class,
            parentColumns = ["route_id"],
            childColumns = ["route_id"],
            onDelete = ForeignKey.RESTRICT
        )
    ],
    indices = [
        Index("client_id"),
        Index("route_id")
    ]
)
data class RouteCollectionEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "route_collection_id")
    val routeCollectionId: Int = 0,

    val state: String,
    @ColumnInfo(name = "notes_for_delivery_note")
    val notesForDeliveryNote: String?,
    val observations: String?,

    @ColumnInfo(name = "client_signature",typeAffinity = ColumnInfo.BLOB)
    val clientSignature: ByteArray?,

    @ColumnInfo(name = "client_id")
    val clientId: Int,

    @ColumnInfo(name = "route_id")
    val routeId: Int
)