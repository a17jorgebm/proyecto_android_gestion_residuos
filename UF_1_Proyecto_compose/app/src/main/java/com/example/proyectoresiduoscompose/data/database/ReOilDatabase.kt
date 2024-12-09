package com.example.proyectoresiduoscompose.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.proyectoresiduoscompose.data.dao.AddressDao
import com.example.proyectoresiduoscompose.data.dao.ClientDao
import com.example.proyectoresiduoscompose.data.dao.ContainerDao
import com.example.proyectoresiduoscompose.data.dao.ResidueDao
import com.example.proyectoresiduoscompose.data.dao.RouteCollectionDao
import com.example.proyectoresiduoscompose.data.dao.RouteDao
import com.example.proyectoresiduoscompose.data.dao.TruckDao
import com.example.proyectoresiduoscompose.data.dao.TruckerDao
import com.example.proyectoresiduoscompose.data.entity.AddressEntity
import com.example.proyectoresiduoscompose.data.entity.ClientEntity
import com.example.proyectoresiduoscompose.data.entity.ContainerEntity
import com.example.proyectoresiduoscompose.data.entity.ResidueEntity
import com.example.proyectoresiduoscompose.data.entity.RouteCollectionEntity
import com.example.proyectoresiduoscompose.data.entity.RouteEntity
import com.example.proyectoresiduoscompose.data.entity.TruckEntity
import com.example.proyectoresiduoscompose.data.entity.TruckerEntity

@Database(
    entities = [
        TruckerEntity::class,
        TruckEntity::class,
        RouteEntity::class,
        RouteCollectionEntity::class,
        ResidueEntity::class,
        ContainerEntity::class,
        ClientEntity::class,
        AddressEntity::class
    ],
    version = 2
)
abstract class ReOilDatabase: RoomDatabase() {
    abstract fun clientDao(): ClientDao
    abstract fun containerDao(): ContainerDao
    abstract fun residueDao(): ResidueDao
    abstract fun routeCollectionDao(): RouteCollectionDao
    abstract fun routeDao(): RouteDao
    abstract fun truckDao(): TruckDao
    abstract fun truckerDao(): TruckerDao
    abstract fun addressDao(): AddressDao
}