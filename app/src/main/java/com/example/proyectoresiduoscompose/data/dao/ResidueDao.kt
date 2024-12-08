package com.example.proyectoresiduoscompose.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.proyectoresiduoscompose.data.entity.ResidueEntity

@Dao
interface ResidueDao {

    // Insert a single Residue
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(residue: ResidueEntity)

    // Insert a list of Residues
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(residues: List<ResidueEntity>)

    // Update a Residue
    @Update
    suspend fun update(residue: ResidueEntity)

    // Delete a Residue
    @Delete
    suspend fun delete(residue: ResidueEntity)

    // Get all Residues
    @Query("SELECT * FROM residue")
    suspend fun getAllResidues(): List<ResidueEntity>

    // Get a Residue by its ID
    @Query("SELECT * FROM residue WHERE residue_id = :residueId")
    suspend fun getResidueById(residueId: Int): ResidueEntity?

    // Get a Residue by its name
    @Query("SELECT * FROM residue WHERE name = :name")
    suspend fun getResidueByName(name: String): ResidueEntity?
}