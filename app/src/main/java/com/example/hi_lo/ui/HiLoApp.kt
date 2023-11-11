package com.example.hi_lo

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import com.example.hi_lo.ui.SetupMatch

@Composable
fun HiLoApp(
  modifier: Modifier = Modifier,
  viewModel: MatchViewModel = MatchViewModel(),
  navController: NavHostController = rememberNavController(),
) {
  val scaffoldState = rememberScaffoldState()
  val title: String by viewModel.title.observeAsState("")

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
//        startDestination = MatchScreen.SCORE.name,
        modifier = modifier.padding(padding)
      ) {

        composable(route = MatchScreen.START.name) {
          SetupMatch(viewModel, navController)
        }
        composable(route = MatchScreen.SCORE.name) {
          EnterScore(viewModel, navController)
        }
        composable(route = MatchScreen.SUMMARY.name) {
          Column(modifier = Modifier.padding(12.dp)) {
            Text(text = "Summary View")
            Spacer(modifier = Modifier.weight(1.0f))
            Button(modifier = Modifier
              .fillMaxWidth()
              .height(48.dp),
                   content = { Text(text = "Finish") },
                   onClick = {
                     navController.navigate(MatchScreen.SETTLE.name)
                   })
          }
        }
        composable(route = MatchScreen.SETTLE.name) {
          Column(modifier = Modifier.padding(12.dp)) {
            Text(text = "Settle Up : ${viewModel.team1Score.value} \t ${viewModel.team2Score.value}")
            Button(modifier = Modifier
              .fillMaxWidth()
              .height(48.dp),
                   content = { Text(text = "Start New Match") },
                   onClick = {
                     viewModel.resetMatch()
                     navController.navigate(MatchScreen.START.name)
                   })
          }
        }
      }
    })
}
