package com.example.proyectoresiduoscompose.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.proyectoresiduoscompose.data.entity.TruckerEntity

@Dao
interface TruckerDao {

    // Insert a single Trucker
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTrucker(trucker: TruckerEntity): Long

    // Insert multiple Truckers
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTruckers(truckers: List<TruckerEntity>): List<Long>

    // Retrieve all Truckers
    @Query("SELECT * FROM trucker")
    suspend fun getAllTruckers(): List<TruckerEntity>

    // Retrieve a Trucker by ID
    @Query("SELECT * FROM trucker WHERE trucker_id = :truckerId")
    suspend fun getTruckerById(truckerId: Int): TruckerEntity?

    // Retrieve Truckers assigned to a specific Truck
    @Query("SELECT * FROM trucker WHERE truck_id = :truckId")
    suspend fun getTruckersByTruckId(truckId: Int): List<TruckerEntity>

    // Update a Trucker
    @Update
    suspend fun updateTrucker(trucker: TruckerEntity): Int

    // Delete a Trucker
    @Delete
    suspend fun deleteTrucker(trucker: TruckerEntity): Int

    // Delete all Truckers
    @Query("DELETE FROM trucker")
    suspend fun deleteAllTruckers(): Int
}