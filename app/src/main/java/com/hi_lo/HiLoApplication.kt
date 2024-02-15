package com.hi_lo

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import timber.log.Timber.DebugTree

@HiltAndroidApp
class HiLoApplication : Application() {
  override fun onCreate() {
    super.onCreate()
    Timber.plant(DebugTree())
  }
}