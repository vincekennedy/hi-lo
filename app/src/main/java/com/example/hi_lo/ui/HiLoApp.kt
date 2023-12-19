package com.example.hi_lo

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.primarySurface
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.hi_lo.data.MatchScreen
import com.example.hi_lo.data.MatchViewModel
import com.example.hi_lo.ui.EnterScore
import com.example.hi_lo.ui.ScoringSummary
import com.example.hi_lo.ui.SettleScreen
import com.example.hi_lo.ui.SetupMatch

@Composable
fun HiLoApp(
  modifier: Modifier = Modifier,
  matchViewModel: MatchViewModel = MatchViewModel(),
  navController: NavHostController = rememberNavController(),
) {
  val scaffoldState = rememberScaffoldState()
  val title: String by matchViewModel.title.observeAsState("")

  Scaffold(
    scaffoldState = scaffoldState,
    topBar = {
      TopAppBar(
        navigationIcon = null, title = {
          Text(text = title)
        }, backgroundColor = MaterialTheme.colors.primarySurface
      )
    },
    content = { padding ->
      NavHost(
        navController = navController,
        startDestination = MatchScreen.START.name,
//        startDestination = MatchScreen.SUMMARY.name,
        modifier = modifier.padding(padding)
      ) {

        composable(route = MatchScreen.START.name) {
          SetupMatch(matchViewModel, navController)
        }
        composable(route = MatchScreen.SCORE.name) {
          EnterScore(matchViewModel, navController)
        }
        composable(route = MatchScreen.SUMMARY.name) {
          ScoringSummary(matchViewModel, navController)
        }
        composable(route = MatchScreen.SETTLE.name) {
          SettleScreen(matchViewModel = matchViewModel, navController = navController)
        }
      }
    })
}
