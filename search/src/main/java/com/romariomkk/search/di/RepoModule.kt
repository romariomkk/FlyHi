package com.romariomkk.search.di

import com.romariomkk.core.util.ErrorParser
import com.romariomkk.search.api.AvailabilityApiErrorParser
import com.romariomkk.search.data.repo.FlightsRepoImpl
import com.romariomkk.search.data.repo.contract.FlightsRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepoModule {

    @Binds
    fun getFlightsRepo(repo: FlightsRepoImpl): FlightsRepo

    @Binds
    fun getFlightApiErrorParser(errorParser: AvailabilityApiErrorParser): ErrorParser
}