package com.romariomkk.destinations.di

import com.google.gson.Gson
import com.romariomkk.destinations.BuildConfig
import com.romariomkk.destinations.api.DestinationsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    @Named(DestinationsApi.TAG)
    fun destinationsRetrofit(gson: Gson, okHttpClient: OkHttpClient) =
        getRetrofit(BuildConfig.DESTINATIONS_API, gson, okHttpClient)

    private fun getRetrofit(baseUrl: String, gson: Gson, okHttpClient: OkHttpClient) =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun destinationsApi(@Named(DestinationsApi.TAG) retrofit: Retrofit): DestinationsApi =
        retrofit.create(DestinationsApi::class.java)
}