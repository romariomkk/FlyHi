package com.romariomkk.search.domain.usecase

import com.romariomkk.core.util.mapData
import com.romariomkk.search.data.repo.contract.FlightsRepo
import com.romariomkk.search.domain.pojo.FlightRequest
import com.romariomkk.search.domain.usecase.contract.SearchFlightsUseCase
import com.romariomkk.search.domain.usecase.mapper.toFlights
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SearchFlightsUseCaseImpl @Inject constructor(
    private val flightsRepo: FlightsRepo
) : SearchFlightsUseCase {

    override suspend fun search(flightRequest: FlightRequest, maxPrice: Int) =
        withContext(Dispatchers.IO) {
            val (_, departure, _, arrival, date, adults, teens, children) = flightRequest
            flightsRepo.search(departure, arrival, date, adults, teens, children)
                .mapData { response ->
                    response
                        .toFlights()
                        .filter { flight ->
                            (flight.fare?.amount ?: 0f) < maxPrice
                        }
                }
        }
}