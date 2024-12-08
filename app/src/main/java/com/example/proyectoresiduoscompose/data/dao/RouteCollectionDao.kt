package com.example.proyectoresiduoscompose.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.proyectoresiduoscompose.data.entity.RouteCollectionEntity

@Dao
interface RouteCollectionDao {

    // Insert a single RouteCollection
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(routeCollection: RouteCollectionEntity)

    // Insert a list of RouteCollections
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(routeCollections: List<RouteCollectionEntity>)

    // Update a RouteCollection
    @Update
    suspend fun update(routeCollection: RouteCollectionEntity)

    // Delete a RouteCollection
    @Delete
    suspend fun delete(routeCollection: RouteCollectionEntity)

    // Get all RouteCollections
    @Query("SELECT * FROM route_collection")
    suspend fun getAllRouteCollections(): List<RouteCollectionEntity>

    // Get a RouteCollection by its ID
    @Query("SELECT * FROM route_collection WHERE route_collection_id = :routeCollectionId")
    suspend fun getRouteCollectionById(routeCollectionId: Int): RouteCollectionEntity?

    // Get RouteCollections by Client ID
    @Query("SELECT * FROM route_collection WHERE client_id = :clientId")
    suspend fun getRouteCollectionsByClientId(clientId: Int): List<RouteCollectionEntity>

    // Get RouteCollections by Route ID
    @Query("SELECT * FROM route_collection WHERE route_id = :routeId")
    suspend fun getRouteCollectionsByRouteId(routeId: Int): List<RouteCollectionEntity>

    // Get RouteCollections based on their state
    @Query("SELECT * FROM route_collection WHERE state = :state")
    suspend fun getRouteCollectionsByState(state: String): List<RouteCollectionEntity>
}