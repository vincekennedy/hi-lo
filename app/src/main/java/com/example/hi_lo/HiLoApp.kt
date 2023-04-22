package com.example.hi_lo

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.primarySurface
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.hi_lo.data.Golfer
import com.example.hi_lo.data.MatchScreen
import com.example.hi_lo.data.MatchViewModel
import com.example.hi_lo.data.Team
import com.example.hi_lo.data.course
import com.example.hi_lo.ui.PlayerScore

@Composable
fun HiLoApp(
  modifier: Modifier = Modifier,
  viewModel: MatchViewModel = MatchViewModel(),
  navController: NavHostController = rememberNavController(),
) {
  val scaffoldState = rememberScaffoldState()

  val golfer1Name = remember { mutableStateOf("") }
  val golfer1Hcp = remember { mutableStateOf("") }
  val golfer2Name = remember { mutableStateOf("") }
  val golfer2Hcp = remember { mutableStateOf("") }
  val golfer3Name = remember { mutableStateOf("") }
  val golfer3Hcp = remember { mutableStateOf("") }
  val golfer4Name = remember { mutableStateOf("") }
  val golfer4Hcp = remember { mutableStateOf("") }

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
        modifier = modifier.padding(padding)
      ) {

        composable(route = MatchScreen.START.name) {
          Column(modifier = Modifier.padding(8.dp)) {
            Text("Team 1")
            EnterGolfer(name = golfer1Name, handicap = golfer1Hcp)
            EnterGolfer(name = golfer2Name, handicap = golfer2Hcp)
            Spacer(modifier = Modifier.height(24.dp))
            Text("Team 2")
            EnterGolfer(name = golfer3Name, handicap = golfer3Hcp)
            EnterGolfer(name = golfer4Name, handicap = golfer4Hcp)
            Spacer(modifier = Modifier.weight(1.0f))
            Button(modifier = Modifier
              .fillMaxWidth()
              .height(48.dp),
                   onClick = {
                     viewModel.startMatch(
                       Team(
                         Golfer(golfer1Name.value, golfer1Hcp.value.toInt()),
                         Golfer(golfer2Name.value, golfer2Hcp.value.toInt())
                       ),
                       Team(
                         Golfer(golfer3Name.value, golfer3Hcp.value.toInt()),
                         Golfer(golfer4Name.value, golfer4Hcp.value.toInt())
                       )
                     )
                     navController.navigate(MatchScreen.SCORE.name)
                   }) {
              Text(text = "Start Match")
            }
          }
        }
        composable(route = MatchScreen.SCORE.name) {
          val hole = course[viewModel.hole.value!! - 1]
          Column(modifier = Modifier.padding(12.dp)) {
            Spacer(modifier = Modifier.height(12.dp))
            PlayerScore(viewModel.team1.golfer1, hole.second)
            PlayerScore(viewModel.team1.golfer2, hole.second)
            Spacer(modifier = Modifier.height(48.dp))
            PlayerScore(viewModel.team2.golfer1, hole.second)
            PlayerScore(viewModel.team2.golfer2, hole.second)
            Spacer(modifier = Modifier.weight(1.0f))
            Button(modifier = Modifier
              .fillMaxWidth()
              .height(48.dp),
                   content = {
                     if (viewModel.hasNextHole()) {
                       Text(text = "Next Hole")
                     } else {
                       Text(text = "Finish")
                     }
                   },
                   onClick = {
                     viewModel.addPointsToTeam1Score(1)
                     viewModel.addPointsToTeam2Score(2)
                     if (viewModel.hasNextHole()) {
                       viewModel.nextHole()
                       navController.navigate(MatchScreen.SCORE.name)
                     } else {
                       navController.navigate(MatchScreen.SUMMARY.name)
                     }
                   })
          }
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

@Composable
private fun EnterGolfer(
  name: MutableState<String>,
  handicap: MutableState<String>
) {
  Row(modifier = Modifier.padding(4.dp)) {
    OutlinedTextField(value = name.value,
                      label = { Text("Name") },
                      onValueChange = {
                        name.value = it
                      }
    )
    Spacer(modifier = Modifier.width(12.dp))
    OutlinedTextField(
      value = handicap.value,
      label = { Text("HCP") },
      onValueChange = {
        handicap.value = it
      },
      keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
  }
}