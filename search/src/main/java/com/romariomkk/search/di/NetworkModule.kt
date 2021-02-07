package com.romariomkk.search.di

import com.google.gson.Gson
import com.romariomkk.search.BuildConfig
import com.romariomkk.search.api.AvailabilityApi
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
    @Named(AvailabilityApi.TAG)
    fun availabilityRetrofit(gson: Gson, okHttpClient: OkHttpClient) =
        getRetrofit(BuildConfig.AVAILABILITY_API, gson, okHttpClient)

    private fun getRetrofit(baseUrl: String, gson: Gson, okHttpClient: OkHttpClient) =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()

    @Provides
    fun availabilityApi(@Named(AvailabilityApi.TAG) retrofit: Retrofit): AvailabilityApi =
        retrofit.create(AvailabilityApi::class.java)

}