package com.example.orchid.repository

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface ProductHelperModule {

    @Binds
    fun getProductHelper(networkProductHelper: NetworkProductHelper): ProductHelper
}