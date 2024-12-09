package com.example.proyectoresiduoscompose.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.proyectoresiduoscompose.data.entity.RouteEntity
import com.example.proyectoresiduoscompose.data.entity.model.TruckWithRoutes

@Dao
interface RouteDao {

    // Insert a single Route
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(route: RouteEntity)

    // Insert a list of Routes
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(routes: List<RouteEntity>)

    // Update a Route
    @Update
    suspend fun update(route: RouteEntity)

    // Delete a Route
    @Delete
    suspend fun delete(route: RouteEntity)

    // Get all Routes
    @Query("SELECT * FROM route")
    suspend fun getAllRoutes(): List<RouteEntity>

    // Get a Route by ID
    @Query("SELECT * FROM route WHERE route_id = :routeId")
    suspend fun getRouteById(routeId: Int): RouteEntity?

    // Get Routes associated with a specific Truck (if any)
    @Query("SELECT * FROM route WHERE truck_id = :truckId")
    suspend fun getRoutesByTruck(truckId: Int): List<RouteEntity>

    @Transaction
    @Query("SELECT * FROM truck WHERE truck_id = :truckId")
    suspend fun getTruckWithRoutes(truckId: Int): TruckWithRoutes

    // Get Routes based on their state
    @Query("SELECT * FROM route WHERE state = :state")
    suspend fun getRoutesByState(state: String): List<RouteEntity>

    // Get Routes within a date range
    @Query("SELECT * FROM route WHERE start_date >= :startDate AND end_date <= :endDate")
    suspend fun getRoutesInDateRange(startDate: Long, endDate: Long): List<RouteEntity>


}