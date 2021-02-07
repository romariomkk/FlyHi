package com.romariomkk.search.di

import com.romariomkk.search.domain.usecase.SearchFlightsUseCaseImpl
import com.romariomkk.search.domain.usecase.contract.SearchFlightsUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface UseCaseModule {
    @Binds
    fun searchFlightsUseCase(useCase: SearchFlightsUseCaseImpl): SearchFlightsUseCase
}