package com.example.hi_lo.ui

import androidx.compose.foundation.layout.padding
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.hi_lo.data.Course
import com.example.hi_lo.data.Hole
import com.example.hi_lo.data.MatchScreen
import com.example.hi_lo.data.MatchViewModel

private val holes = listOf(
  Hole(1, 7, 4),
  Hole(2, 1, 4),
  Hole(3, 3, 4),
  Hole(4, 13, 4),
  Hole(5, 15, 3),
  Hole(6, 9, 5),
  Hole(7, 5, 4),
  Hole(8, 11, 3),
  Hole(9, 17, 5),
  Hole(10, 6, 4),
  Hole(11, 2, 4),
  Hole(12, 10, 4),
  Hole(13, 14, 5),
  Hole(14, 8, 4),
  Hole(15, 18, 3),
  Hole(16, 16, 5),
  Hole(17, 4, 4),
  Hole(18, 12, 3),
)

val course = Course(name = "Maple Creek", slope = 134, rating = 72.5f, holes = holes)

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
      }
    })
}
