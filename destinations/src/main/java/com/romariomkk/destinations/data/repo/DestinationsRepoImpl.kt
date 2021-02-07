package com.romariomkk.destinations.data.repo

import com.romariomkk.core.util.Resource
import com.romariomkk.destinations.api.DestinationsApi
import com.romariomkk.destinations.data.db.DestinationsDao
import com.romariomkk.destinations.data.db.pojo.DBDestination
import com.romariomkk.destinations.data.repo.contract.DestinationsRepo
import com.romariomkk.destinations.domain.mapper.toDBDestination
import com.romariomkk.destinations.util.Keys
import timber.log.Timber
import javax.inject.Inject

class DestinationsRepoImpl @Inject constructor(
    private val apiDestinations: DestinationsApi,
    private val daoDestinations: DestinationsDao
) : DestinationsRepo {

    override suspend fun retrieveAll(): Resource<Nothing> {
        return try {
            if (!daoDestinations.isAnyItemAvailable()) {
                val all = apiDestinations.getAll().stations
                    .map { it.toDBDestination() }
                daoDestinations.saveAll(all)

                Resource.success()
            } else {
                Resource.success()
            }
        } catch (t: Throwable) {
            Timber.e(t)
            Resource.error(t)
        }
    }

    override suspend fun search(query: String): Resource<List<DBDestination>> {
        var searchResults = daoDestinations.search(query)
        if (query.isEmpty() && searchResults.isEmpty()) {
            if (retrieveAll().isError())
                return Resource.error(Throwable(Keys.Errors.ERR_FAILED_TO_RETRIEVE))

            searchResults = daoDestinations.search(query)
        }
        return Resource.success(searchResults)
    }
}