package com.kapsta.handbagshop.framework.application

import android.app.Application
import android.content.ComponentCallbacks2
import android.content.res.Configuration
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        // Application level initialization
    }

    override fun onTerminate() {
        super.onTerminate()
        // Called when the application is terminated
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        // Called when the configuration (e.g., orientation, locale) changes
    }

    override fun onLowMemory() {
        super.onLowMemory()
        // Called when the system is running low on memory
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
        // Called when the system requests to trim memory
        when (level) {
            ComponentCallbacks2.TRIM_MEMORY_RUNNING_MODERATE -> {
                // Perform memory cleanup for moderate memory pressure
            }

            ComponentCallbacks2.TRIM_MEMORY_RUNNING_LOW -> {
                // Perform memory cleanup for low memory pressure
            }

            ComponentCallbacks2.TRIM_MEMORY_RUNNING_CRITICAL -> {
                // Perform memory cleanup for critical memory pressure
            }
            // Handle other memory trim levels if necessary
        }
    }
}
