package com.example.fetchproject.domain.usecase
import com.example.fetchproject.domain.model.Item
import com.example.fetchproject.data.repository.ItemRepository

class GetItemsUseCase(private val repository: ItemRepository) {
    suspend fun execute(): List<Item> {
        return repository.getItems()
            .filter {!it.name.isNullOrBlank()}
            .sortedWith(compareBy({ it.listId}, {it.name}))
    }
}