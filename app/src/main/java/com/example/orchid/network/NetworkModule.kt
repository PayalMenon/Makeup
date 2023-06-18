package com.example.orchid.network

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    val BASE_URL = "http://makeup-api.herokuapp.com/api/v1/"

    @Provides
    @Singleton
    fun providesRetrofit(): Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun providesNetworkService(retrofit: Retrofit): NetworkService = retrofit.create(NetworkService::class.java)
}
