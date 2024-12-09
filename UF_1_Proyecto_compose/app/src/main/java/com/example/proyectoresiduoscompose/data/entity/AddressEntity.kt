package com.example.proyectoresiduoscompose.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "address")
data class AddressEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "address_id")
    val addressId: Int = 0,

    @ColumnInfo(name = "address_text")
    val addressText: String,
    val longitude: Int,
    val latitude: Int
)