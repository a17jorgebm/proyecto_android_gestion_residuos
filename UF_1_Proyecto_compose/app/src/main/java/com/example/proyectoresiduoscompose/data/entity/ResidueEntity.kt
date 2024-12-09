package com.example.proyectoresiduoscompose.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "residue")
data class ResidueEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "residue_id")
    val residueId: Int = 0,

    val name: String
)