package com.example.proyectoresiduoscompose.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "container",
    foreignKeys = [
        ForeignKey(
            entity = ClientEntity::class,
            parentColumns = ["client_id"],
            childColumns = ["client_id"],
            onDelete = ForeignKey.RESTRICT
        ),
        ForeignKey(
            entity = ResidueEntity::class,
            parentColumns = ["residue_id"],
            childColumns = ["residue_id"],
            onDelete = ForeignKey.RESTRICT
        )
    ],
    indices = [
        Index("client_id"),
        Index("residue_id")
    ]
)
data class ContainerEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "container_id")
    val containerId: Int = 0,

    val capacity: Int,
    val name: String,

    //foreing keys
    @ColumnInfo(name = "residue_id")
    val residueEntity: Int,

    @ColumnInfo(name = "client_id")
    val clientEntity: Int
)