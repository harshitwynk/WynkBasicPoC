package com.example.harshitjain.wynkbasicpoc.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database(entities = [Item::class, Collection::class], version = 2)
abstract class WynkDB : RoomDatabase() {

    abstract fun itemDao(): ItemDao
    abstract fun collectionDao(): CollectionDao
}