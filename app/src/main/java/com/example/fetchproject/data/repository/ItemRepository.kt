package com.example.fetchproject.data.repository
import com.example.fetchproject.domain.model.Item
interface ItemRepository {
    suspend fun getItems(): List<Item>
}