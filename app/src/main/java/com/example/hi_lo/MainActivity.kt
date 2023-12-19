package com.example.hi_lo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.hi_lo.ui.HiLoApp
import com.example.hi_lo.ui.theme.HiLoTheme

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