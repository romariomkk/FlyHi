package com.romariomkk.search.data.repo.contract

import com.romariomkk.core.util.Resource
import com.romariomkk.search.api.pojo.FlightsResponseDTO
import java.util.*

interface FlightsRepo {
    suspend fun search(
        departure: String, arrival: String, date: Date,
        adults: Int, teens: Int, children: Int
    ): Resource<FlightsResponseDTO>
}