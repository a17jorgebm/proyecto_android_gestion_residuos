package com.example.proyectoresiduoscompose.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.proyectoresiduoscompose.data.entity.AddressEntity

@Dao
interface AddressDao {

    // Insert a new address
    @Insert
    suspend fun insertAddress(address: AddressEntity)

    // Insert multiple addresses
    @Insert
    suspend fun insertAddresses(addresses: List<AddressEntity>): List<Long>

    // Update an existing address
    @Update
    suspend fun updateAddress(address: AddressEntity)

    // Delete a single address
    @Delete
    suspend fun deleteAddress(address: AddressEntity)

    // Delete all addresses
    @Query("DELETE FROM address")
    suspend fun deleteAllAddresses()

    // Get all addresses
    @Query("SELECT * FROM address")
    suspend fun getAllAddresses(): List<AddressEntity>

    // Get a specific address by ID
    @Query("SELECT * FROM address WHERE address_id = :id")
    suspend fun getAddressById(id: Int): AddressEntity?
}