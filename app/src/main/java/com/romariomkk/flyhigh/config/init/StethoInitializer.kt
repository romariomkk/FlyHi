package com.romariomkk.flyhigh.config.init

import android.content.Context
import com.facebook.stetho.Stetho
import com.romariomkk.flyhigh.BuildConfig
import com.romariomkk.flyhigh.config.init.Initializer
import javax.inject.Inject
import javax.inject.Singleton

class StethoInitializer @Inject constructor(context: Context): Initializer {
    init {
        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(context)
        }
    }
}