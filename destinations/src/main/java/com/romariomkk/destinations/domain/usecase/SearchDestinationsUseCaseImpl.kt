package com.romariomkk.destinations.domain.usecase

import com.romariomkk.core.util.Resource
import com.romariomkk.core.util.mapData
import com.romariomkk.destinations.data.repo.contract.DestinationsRepo
import com.romariomkk.destinations.domain.mapper.toDestination
import com.romariomkk.destinations.domain.pojo.Destination
import com.romariomkk.destinations.domain.usecase.contract.SearchDestinationsUseCase
import javax.inject.Inject

class SearchDestinationsUseCaseImpl @Inject constructor(
    private val destinationsRepo: DestinationsRepo
): SearchDestinationsUseCase {

    override suspend fun searchDestinations(query: String): Resource<List<Destination>> {
        val searchResults = destinationsRepo.search(query)
        return searchResults.mapData { dbDestinations -> dbDestinations.map { it.toDestination() } }
    }
}