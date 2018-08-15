package com.example.harshitjain.wynkbasicpoc.repo

import android.arch.lifecycle.LiveData
import android.util.Log
import com.example.harshitjain.wynkbasicpoc.db.Collection
import com.example.harshitjain.wynkbasicpoc.db.CollectionDao
import com.example.harshitjain.wynkbasicpoc.db.Item
import com.example.harshitjain.wynkbasicpoc.db.ItemDao
import com.example.harshitjain.wynkbasicpoc.network.ApiService

class ItemRepository(private val appExecutors: AppExecutors, private val apiService: ApiService, private val itemDao: ItemDao, val collectionDao: CollectionDao) {

    fun loadItem(id: String, type: String, count: Int, offset: Int, childType: String): LiveData<Resource<List<Item>>> {
        return object : NetworkBoundResource<List<Item>, Item>(appExecutors) {
            override fun saveCallResult(entity: Item) {

                Log.v("hjhj", "saveCallResult() : | entityID=" + entity.id + "  |  title=" + entity.title)
                itemDao.insertItem(entity)
                saveChildItems(entity.items)
                updateCollection(entity)
            }

            override fun shouldFetch(data: List<Item>?): Boolean {
                Log.v("hjhj", "shouldFetch() : | data=" + data + "  |  data size=" + data?.size)
                return data == null || data.isEmpty()
//                return true
            }

            override fun loadFromDb(): LiveData<List<Item>> {
                Log.v("hjhj", "loadFromDb() : ")
//                return itemDao.loadItemById(id)
//                return itemDao.loadItemList(childType)
                return getItem(id, type)
            }

            override fun createCall(): LiveData<ApiResponse<Item>> {
                Log.v("hjhj", "createCall() : |")
                return apiService.getItem(id, type, count, offset)
            }

            override fun onFetchFailed() {

            }

        }.asLiveData()
    }

    private fun saveChildItems(items: List<Item>?) {
        items?.let { itemDao.insertItemList(it) }
    }

    private fun updateCollection(entity: Item) {

        var rank = entity.offset
        val collections = mutableListOf<Collection>()


        entity.items?.forEach {
            val collection = Collection(entity.id, it.id, it.title!!, rank!!)
            collections.add(collection)
            rank++
        }

        if (collections.size > 0) {
            collectionDao.insertCollectionList(collections)
        }
    }

    private fun getItem(id: String, type: String): LiveData<List<Item>> {
        return itemDao.loadSongItems(id)
    }
}