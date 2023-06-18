package com.example.orchid.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.orchid.data.Product

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAll(products: List<ProductEntity>)

    @Query(" SELECT * from product")
    fun getAll(): List<ProductEntity>

    @Query("SELECT * from product where id = :productId")
    fun getProduct(productId: Int) : Product
}