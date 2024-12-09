package com.example.proyectoresiduoscompose.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "client",
    foreignKeys = [
        ForeignKey(
            entity = AddressEntity::class,
            parentColumns = ["address_id"],
            childColumns = ["address_id"],
            onDelete = ForeignKey.RESTRICT
        )
    ],
    indices = [
        Index("address_id")
    ]
)
data class ClientEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "client_id")
    val clientId: Int = 0,

    val nnss: Int,
    val name: String,

    @ColumnInfo(name = "address_id")
    val addressId: Int?
)