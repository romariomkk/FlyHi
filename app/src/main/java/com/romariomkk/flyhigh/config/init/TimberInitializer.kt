package com.romariomkk.flyhigh.config.init

import com.romariomkk.flyhigh.BuildConfig
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

class TimberInitializer @Inject constructor(): Initializer {
    init {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}