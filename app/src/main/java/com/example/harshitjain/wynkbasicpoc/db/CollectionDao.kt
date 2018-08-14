package com.example.harshitjain.wynkbasicpoc.db

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*

@Dao
abstract class CollectionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertCollectionList(collections: List<Collection>?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertCollection(collection: Collection?)

    @Delete
    abstract fun deleteCollection(collection: Collection?)

    @Delete
    abstract fun deleteCollectionList(collections: List<Collection>?)

    @Query("select * from Collection")
    abstract fun loadCollectionList(type: String): LiveData<List<Collection>>

    @Query("select * from Collection")
    abstract fun loadCollectionById(id: String): LiveData<Collection>

    @Query("delete from Collection")
    abstract fun clearCollections()
}