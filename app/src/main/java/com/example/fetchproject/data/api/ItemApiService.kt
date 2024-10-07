package com.example.fetchproject.data.api
import com.example.fetchproject.domain.model.Item
import retrofit2.http.GET
interface ItemApiService {
    @GET("https://fetch-hiring.s3.amazonaws.com/hiring.json")
    suspend fun fetchItems(): List<Item>
}