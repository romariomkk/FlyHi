package com.romariomkk.destinations.data.repo.contract

import com.romariomkk.core.util.Resource
import com.romariomkk.destinations.data.db.pojo.DBDestination

interface DestinationsRepo {
    suspend fun retrieveAll(): Resource<Nothing>
    suspend fun search(query: String): Resource<List<DBDestination>>
}