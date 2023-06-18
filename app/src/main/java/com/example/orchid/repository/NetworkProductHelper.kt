package com.example.orchid.repository

import com.example.orchid.data.Product
import com.example.orchid.database.ProductDao
import com.example.orchid.database.ProductDatabase
import com.example.orchid.database.getProductEntityFromProduct
import com.example.orchid.database.getProductFromProductEntity
import com.example.orchid.network.NetworkService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NetworkProductHelper @Inject constructor(
    private val networkService: NetworkService,
    private val productDao: ProductDao
) : ProductHelper {

    override suspend fun fetchProductList(): List<Product> {
        return withContext(Dispatchers.IO) {
            if(productDao.getAll().isEmpty()) {
                val list = fetchListRemotely()
                System.out.println("Payal Logs - fetched list size = ${list.size}")
                list
            } else {
                val fetched = productDao.getAll()
                val list = fetched.map { entity ->
                    entity.getProductFromProductEntity()
                }
                list
            }
        }
    }

    override suspend fun fetchProduct(productId: Int): Product {
        return withContext(Dispatchers.IO) {
            productDao.getProduct(productId)
        }
    }

    private suspend fun fetchListRemotely() : List<Product>{
        val list = networkService.getProducts()
        val fetched = list.map { product ->
            product.getProductEntityFromProduct()
        }
        productDao.addAll(fetched)
        return list
    }
}