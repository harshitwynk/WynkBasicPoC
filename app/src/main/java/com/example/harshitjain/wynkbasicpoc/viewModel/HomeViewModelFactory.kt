package com.example.harshitjain.wynkbasicpoc.viewModel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.harshitjain.wynkbasicpoc.repo.ItemRepository

class HomeViewModelFactory(private val itemRepository: ItemRepository) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel(itemRepository) as T
    }
}