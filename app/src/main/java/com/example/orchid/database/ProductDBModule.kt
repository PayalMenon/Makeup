package com.example.orchid.database

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ProductDBModule {

    @Provides
    fun providesDatabase(@ApplicationContext context: Context): ProductDatabase =
        Room.databaseBuilder( context,
            ProductDatabase::class.java,
            "product.db"
        ).build()

    @Provides
    fun providesProductDao(database: ProductDatabase): ProductDao = database.productDao()
}