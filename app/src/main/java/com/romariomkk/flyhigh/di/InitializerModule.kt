package com.romariomkk.flyhigh.di

import com.romariomkk.flyhigh.config.init.Initializer
import com.romariomkk.flyhigh.config.init.StethoInitializer
import com.romariomkk.flyhigh.config.init.TimberInitializer
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet

@Module
@InstallIn(SingletonComponent::class)
interface InitializerModule {

    @Binds
    fun stethoInitializer(stethoInitializer: StethoInitializer): Initializer

    @Binds
    fun timberInitializer(timberInitializer: TimberInitializer): Initializer
}