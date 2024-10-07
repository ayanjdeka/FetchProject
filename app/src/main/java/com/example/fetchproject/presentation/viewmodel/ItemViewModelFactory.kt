package com.example.fetchproject.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.fetchproject.domain.usecase.GetItemsUseCase

class ItemViewModelFactory(private val getItemsUseCase: GetItemsUseCase) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ItemViewModel::class.java)) {
            return ItemViewModel(getItemsUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
