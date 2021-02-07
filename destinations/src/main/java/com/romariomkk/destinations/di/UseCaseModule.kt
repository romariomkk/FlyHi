package com.romariomkk.destinations.di

import com.romariomkk.destinations.domain.usecase.RetrieveAllDestinationsUseCaseImpl
import com.romariomkk.destinations.domain.usecase.SearchDestinationsUseCaseImpl
import com.romariomkk.destinations.domain.usecase.contract.RetrieveAllDestinationsUseCase
import com.romariomkk.destinations.domain.usecase.contract.SearchDestinationsUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface UseCaseModule {
    @Binds
    fun searchDestinationsUseCase(useCase: SearchDestinationsUseCaseImpl): SearchDestinationsUseCase

    @Binds
    fun retrieveAllDestinationsUseCase(useCase: RetrieveAllDestinationsUseCaseImpl): RetrieveAllDestinationsUseCase
}