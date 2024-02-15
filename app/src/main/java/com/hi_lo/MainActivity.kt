package com.hi_lo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.hi_lo.ui.HiLoApp
import com.hi_lo.ui.theme.HiLoTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
  //region Lifecycle
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      HiLoTheme {
        HiLoApp()
      }
    }
  }
  //endregion
}