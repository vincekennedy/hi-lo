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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


@Composable
fun HiLoApp(
  modifier: Modifier = Modifier,
  viewModel: MatchViewModel = MatchViewModel(),
  navController: NavHostController = rememberNavController()
) {

  // Get current back stack entry
//  val backStackEntry by navController.currentBackStackEntryAsState()
  // Get the name of the current screen
//  val currentScreen = MatchScreen.valueOf(
//    backStackEntry?.destination?.route ?: MatchScreen.Start.name
//  )

  val golfer1Name = remember { mutableStateOf("") }
  val golfer1Hcp = remember { mutableStateOf("") }
  val golfer2Name = remember { mutableStateOf("") }
  val golfer2Hcp = remember { mutableStateOf("") }
  val golfer3Name = remember { mutableStateOf("") }
  val golfer3Hcp = remember { mutableStateOf("") }
  val golfer4Name = remember { mutableStateOf("") }
  val golfer4Hcp = remember { mutableStateOf("") }

  Scaffold(topBar = { AppBar() }, content = { padding ->
    NavHost(
      navController = navController,
      startDestination = MatchScreen.Start.name,
      modifier = modifier.padding(padding)
    ) {

      composable(route = MatchScreen.Start.name) {
        Column(modifier = Modifier.padding(8.dp)) {
          Text("Team 1")
          EnterGolfer(name = golfer1Name, handicap = golfer1Hcp)
          EnterGolfer(name = golfer2Name, handicap = golfer2Hcp)
          Spacer(modifier = Modifier.height(24.dp))
          Text("Team 2")
          EnterGolfer(name = golfer3Name, handicap = golfer3Hcp)
          EnterGolfer(name = golfer4Name, handicap = golfer4Hcp)
          Spacer(modifier = Modifier.weight(1.0f))
          Button(modifier = Modifier.fillMaxWidth().height(48.dp),
                 onClick = {
                   viewModel.setMatch(
                     Team(
                       Golfer(golfer1Name.value, golfer1Hcp.value.toInt()),
                       Golfer(golfer2Name.value, golfer2Hcp.value.toInt())
                     ),
                     Team(
                       Golfer(golfer3Name.value, golfer1Hcp.value.toInt()),
                       Golfer(golfer4Name.value, golfer2Hcp.value.toInt())
                     )
                   )
                   navController.navigate(MatchScreen.HOLE.name)
                 }) {
            Text(text = "Start Match")
          }
        }
      }
      composable(route = MatchScreen.HOLE.name) {
        HoleScoring(viewModel)
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

@Composable
fun AppBar() {
  TopAppBar(
    navigationIcon = null, title = {
      Text(text = "Hi-Lo")
    }, backgroundColor = MaterialTheme.colors.primarySurface
  )
}

@Composable
fun HoleScoring(matchViewModel: MatchViewModel) {
  Column(modifier = Modifier.padding(12.dp)) {
    Text(text = "Hole : 1   HCP: 11   Par 4")
    Spacer(modifier = Modifier.height(12.dp))
//    playerScore(matchViewModel)
//    playerScore(matchViewModel)
    Spacer(modifier = Modifier.height(48.dp))
//    playerScore(matchViewModel)
//    playerScore(matchViewModel)
    Spacer(modifier = Modifier.weight(1.0f))
    Button(modifier = Modifier
      .fillMaxWidth()
      .height(48.dp),
           content = { Text(text = "Next Hole") },
           onClick = {
           })
  }
}
