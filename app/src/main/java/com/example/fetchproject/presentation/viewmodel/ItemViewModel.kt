package com.example.fetchproject.presentation.viewmodel
import androidx.lifecycle.*
import com.example.fetchproject.domain.model.Item
import com.example.fetchproject.domain.usecase.GetItemsUseCase
import kotlinx.coroutines.launch
class ItemViewModel(private val getItemsUseCase: GetItemsUseCase) : ViewModel() {
    private val _items = MutableLiveData<List<Item>>()
    val items: LiveData<List<Item>> get() = _items

    fun fetchItems() {
        viewModelScope.launch {
            val result = getItemsUseCase.execute()
            _items.postValue(result)
        }
    }

}

