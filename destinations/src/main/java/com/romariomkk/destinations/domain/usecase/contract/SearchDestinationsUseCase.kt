package com.romariomkk.destinations.domain.usecase.contract

import com.romariomkk.core.util.Resource
import com.romariomkk.destinations.domain.pojo.Destination

interface SearchDestinationsUseCase {
    suspend fun searchDestinations(query: String): Resource<List<Destination>>
}