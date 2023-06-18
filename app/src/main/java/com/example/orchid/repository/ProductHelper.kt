package com.example.orchid.repository

import com.example.orchid.data.Product

interface ProductHelper {

    suspend fun fetchProductList(): List<Product>

    suspend fun fetchProduct(productId: Int): Product
}