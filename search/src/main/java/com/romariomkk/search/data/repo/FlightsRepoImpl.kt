package com.romariomkk.search.data.repo

import com.romariomkk.core.util.Resource
import com.romariomkk.search.api.AvailabilityApi
import com.romariomkk.search.api.AvailabilityApiErrorParser
import com.romariomkk.search.api.pojo.FlightsResponseDTO
import com.romariomkk.search.data.repo.contract.FlightsRepo
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class FlightsRepoImpl @Inject constructor(
    private val api: AvailabilityApi,
    private val errorMapper: AvailabilityApiErrorParser
): FlightsRepo {

    private val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.ROOT)

    override suspend fun search(
        departure: String, arrival: String, date: Date,
        adults: Int, teens: Int, children: Int
    ): Resource<FlightsResponseDTO> {
        return try {
            val departureDate = dateFormat.format(date)
            val flightsResponse = api.getFlights(departure, arrival, departureDate, adults, teens, children)
            Resource.success(flightsResponse)
        } catch (t: Throwable) {
            Timber.e(t)
            Resource.error(Throwable(errorMapper.parse(t)))
        }
    }
}