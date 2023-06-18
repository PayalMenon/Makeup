package com.example.orchid.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class OrchidApplication: Application() {

    override fun onCreate() {
        super.onCreate()
    }
}

