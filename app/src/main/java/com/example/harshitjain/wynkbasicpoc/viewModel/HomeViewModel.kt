package com.example.harshitjain.wynkbasicpoc.viewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.example.harshitjain.wynkbasicpoc.db.Item
import com.example.harshitjain.wynkbasicpoc.repo.ItemRepository
import com.example.harshitjain.wynkbasicpoc.repo.Resource

class HomeViewModel(private val itemRepository: ItemRepository) : ViewModel() {


    fun getItem(itemId: String?, type: String?, childType: String): LiveData<Resource<List<Item>>>? {
        if (itemId == null || type == null)
            return null
        return itemRepository.loadItem(itemId, type, 50, 0, childType)
    }

}