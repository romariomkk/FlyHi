package com.romariomkk.destinations.domain.usecase

import com.romariomkk.destinations.data.repo.contract.DestinationsRepo
import com.romariomkk.destinations.domain.usecase.contract.RetrieveAllDestinationsUseCase
import javax.inject.Inject

class RetrieveAllDestinationsUseCaseImpl @Inject constructor(
    private val repo: DestinationsRepo
): RetrieveAllDestinationsUseCase {

    override suspend fun retrieveAll() {
        repo.retrieveAll()
    }
}