package com.example.orchid.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.orchid.data.Product

const val PRODUCT_TABLE = "product"

@Entity(tableName = PRODUCT_TABLE)
data class ProductEntity (
        @PrimaryKey
        val id: Int,
        @ColumnInfo (name = "brand")
        val brand: String,
        @ColumnInfo (name = "product_type")
        val product_type: String,
        @ColumnInfo (name = "image_link")
        val image_link : String,
        @ColumnInfo (name = "name", defaultValue = "")
        val name: String,
        @ColumnInfo (name = "description", defaultValue = "")
        val desc: String,
        @ColumnInfo (name = "price", defaultValue = "")
        val price: String,
        @ColumnInfo (name = "rating", defaultValue = "0.0")
        val rating: Float,
)

fun ProductEntity.getProductFromProductEntity(): Product = Product(
        id = this.id,
        brand = this.brand ?: "",
        product_type = this.product_type ?: "",
        image_link = this.image_link ?: "",
        name = this.name ?: "",
        description = this.desc ?: "",
        price = this.price ?: "",
        rating = this.rating
)

fun Product.getProductEntityFromProduct(): ProductEntity = ProductEntity(
        id = this.id,
        brand = this.brand ?: "",
        product_type = this.product_type ?: "",
        image_link = this.image_link ?: "",
        name = this.name ?: "",
        desc = this.description ?: "",
        price = this.price ?: "",
        rating = this.rating,
)