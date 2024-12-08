package com.example.proyectoresiduoscompose.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.proyectoresiduoscompose.data.entity.TruckEntity

@Dao
interface TruckDao {

    // Insert a single Truck
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTruck(truck: TruckEntity): Long

    // Insert multiple Trucks
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTrucks(trucks: List<TruckEntity>): List<Long>

    // Retrieve all Trucks
    @Query("SELECT * FROM truck")
    suspend fun getAllTrucks(): List<TruckEntity>

    // Retrieve a Truck by ID
    @Query("SELECT * FROM truck WHERE truck_id = :truckId")
    suspend fun getTruckById(truckId: Int): TruckEntity?

    // Update a Truck
    @Update
    suspend fun updateTruck(truck: TruckEntity): Int

    // Delete a Truck
    @Delete
    suspend fun deleteTruck(truck: TruckEntity): Int

    // Delete all Trucks
    @Query("DELETE FROM truck")
    suspend fun deleteAllTrucks(): Int
}