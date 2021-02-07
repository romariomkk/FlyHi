package com.romariomkk.search.api

import com.romariomkk.search.api.pojo.FlightsResponseDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface AvailabilityApi {

    @GET("Availability?roundtrip=false&ToUs=AGREED")
    suspend fun getFlights(
        @Query("origin") departureAirport: String,
                   @Query("destination") destinationAirport: String,
                   @Query("dateout") departureDate: String,
                   @Query("adt") adults: Int,
                   @Query("teen") teens: Int,
                   @Query("chd") children: Int): FlightsResponseDTO

    companion object {
        const val TAG = "AvailabilityApi"
    }
}