package com.example.harshitjain.wynkbasicpoc.repo

import android.arch.lifecycle.LiveData
import android.util.Log
import com.example.harshitjain.wynkbasicpoc.db.Collection
import com.example.harshitjain.wynkbasicpoc.db.Collection.Companion.TOP_PLAYLIST_ID
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

                // Insert dummy data in db to increase its size to more than 10000
                if (itemDao.getItemCount() < DUMMY_DATA_SIZE) {
                    val timestamp = System.currentTimeMillis()
                    val playlist: Item = itemDao.getFirstItemForType("PLAYLIST") ?: return
                    playlist.id = timestamp.toString()
                    playlist.count = DUMMY_DATA_SIZE
                    playlist.offset = 0
                    val song: Item = itemDao.getFirstItemForType("SONG") ?: return

                    val itemsList = mutableListOf<Item>()
                    for (i in 1..DUMMY_DATA_SIZE) {
                        song.id = (timestamp + i).toString()
                        itemsList.add(Item(song))
                    }
                    playlist.items = itemsList
                    itemDao.insertItem(playlist)
                    saveChildItems(playlist.items)
                    updateCollection(playlist)
                    collectionDao.insertCollection(Collection(TOP_PLAYLIST_ID, playlist.id, playlist.title?: "", playlist.offset?: 0))
                }
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

    companion object {
        const val DUMMY_DATA_SIZE = 10000
    }
}