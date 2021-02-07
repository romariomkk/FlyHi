package com.romariomkk.destinations.di

import com.romariomkk.destinations.data.repo.DestinationsRepoImpl
import com.romariomkk.destinations.data.repo.contract.DestinationsRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepoModule {

    @Binds
    fun getDestinationsRepo(repo: DestinationsRepoImpl): DestinationsRepo
}