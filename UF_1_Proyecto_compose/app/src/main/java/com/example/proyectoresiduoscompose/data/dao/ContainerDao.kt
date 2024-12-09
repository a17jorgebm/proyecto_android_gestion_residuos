package com.example.proyectoresiduoscompose.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.proyectoresiduoscompose.data.entity.ContainerEntity

@Dao
interface ContainerDao {

    // Insert a single Container
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(container: ContainerEntity)

    // Insert a list of Containers
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(containers: List<ContainerEntity>)

    // Update a Container
    @Update
    suspend fun update(container: ContainerEntity)

    // Delete a Container
    @Delete
    suspend fun delete(container: ContainerEntity)

    // Get all Containers
    @Query("SELECT * FROM container")
    suspend fun getAllContainers(): List<ContainerEntity>

    // Get a Container by its ID
    @Query("SELECT * FROM container WHERE container_id = :containerId")
    suspend fun getContainerById(containerId: Int): ContainerEntity?

    // Get Containers by Client ID
    @Query("SELECT * FROM container WHERE client_id = :clientId")
    suspend fun getContainersByClientId(clientId: Int): List<ContainerEntity>

    // Get Containers by Residue ID
    @Query("SELECT * FROM container WHERE residue_id = :residueId")
    suspend fun getContainersByResidueId(residueId: Int): List<ContainerEntity>
}