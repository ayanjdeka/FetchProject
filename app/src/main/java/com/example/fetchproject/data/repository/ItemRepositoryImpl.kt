package com.example.fetchproject.data.repository
import com.example.fetchproject.data.api.ItemApiService
import com.example.fetchproject.domain.model.Item

class ItemRepositoryImpl(private val apiService: ItemApiService) : ItemRepository {
    override suspend fun getItems(): List<Item> {
        return apiService.fetchItems()
    }
}