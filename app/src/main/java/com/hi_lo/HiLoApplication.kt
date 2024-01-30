package com.hi_lo

import android.app.Application
import timber.log.Timber
import timber.log.Timber.DebugTree

class HiLoApplication : Application() {
  override fun onCreate() {
    super.onCreate()
    Timber.plant(DebugTree())
  }
}