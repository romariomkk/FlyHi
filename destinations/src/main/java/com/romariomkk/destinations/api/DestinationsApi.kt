package com.romariomkk.destinations.api

import com.romariomkk.destinations.api.pojo.StationsResponseDTO
import retrofit2.http.GET

interface DestinationsApi {
    @GET("/static/stations.json")
    suspend fun getAll(): StationsResponseDTO

    companion object {
        const val TAG = "DestinationsApi"
    }
}