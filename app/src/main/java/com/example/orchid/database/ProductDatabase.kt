package com.example.orchid.database

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RenameColumn
import androidx.room.RoomDatabase
import androidx.room.migration.AutoMigrationSpec

@Database(
    entities = [
        ProductEntity::class,
    ],
    version = 3,
    autoMigrations = [
         AutoMigration(
             from = 1,
             to = 2,
             spec = ProductImageLinkAutoMigrationSpec::class,
         ),
        AutoMigration(
            from = 2,
            to = 3,
        ),
    ],
    exportSchema = true,
)

abstract class ProductDatabase: RoomDatabase() {
    abstract fun productDao() : ProductDao
}


@RenameColumn(
    tableName = PRODUCT_TABLE,
    fromColumnName = "api_featured_image",
    toColumnName = "image_link",
)
class ProductImageLinkAutoMigrationSpec : AutoMigrationSpec