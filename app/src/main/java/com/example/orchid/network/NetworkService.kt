package com.example.orchid.network

import com.example.orchid.data.Product
import retrofit2.http.GET

interface NetworkService {

    @GET("products.json")
    suspend fun getProducts(): List<Product>
}