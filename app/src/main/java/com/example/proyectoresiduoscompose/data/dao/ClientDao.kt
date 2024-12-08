package com.example.proyectoresiduoscompose.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.proyectoresiduoscompose.data.entity.ClientEntity

@Dao
interface ClientDao {

    // Insert a single Client
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(client: ClientEntity)

    // Insert a list of Clients
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(clients: List<ClientEntity>)

    // Update a Client
    @Update
    suspend fun update(client: ClientEntity)

    // Delete a Client
    @Delete
    suspend fun delete(client: ClientEntity)

    // Get all Clients
    @Query("SELECT * FROM client")
    suspend fun getAllClients(): List<ClientEntity>

    // Get a Client by its ID
    @Query("SELECT * FROM client WHERE client_id = :clientId")
    suspend fun getClientById(clientId: Int): ClientEntity?

    // Get Clients by address ID
    @Query("SELECT * FROM client WHERE address_id = :addressId")
    suspend fun getClientsByAddressId(addressId: Int): List<ClientEntity>
}