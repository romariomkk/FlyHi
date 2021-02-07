package com.romariomkk.search.domain.usecase.contract

import com.romariomkk.core.util.Resource
import com.romariomkk.search.domain.pojo.Flight
import com.romariomkk.search.domain.pojo.FlightRequest

interface SearchFlightsUseCase {
    suspend fun search(flightRequest: FlightRequest, maxPrice: Int): Resource<List<Flight>>
}