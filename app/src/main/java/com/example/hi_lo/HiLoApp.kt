package com.example.hi_lo

import android.widget.Toast
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController


@Composable
fun HiLoApp(
  modifier: Modifier = Modifier,
  viewModel: MatchViewModel = MatchViewModel(),
  navController: NavHostController = rememberNavController()
) {

  // Get current back stack entry
  val backStackEntry by navController.currentBackStackEntryAsState()
  // Get the name of the current screen
  val currentScreen = MatchScreen.valueOf(
    backStackEntry?.destination?.route ?: MatchScreen.Start.name
  )

  Scaffold(topBar = { AppBar() }, content = { padding ->
    NavHost(
      navController = navController,
      startDestination = MatchScreen.Start.name,
      modifier = modifier.padding(padding)
    ) {
      composable(route = MatchScreen.Start.name) {
        MatchSetupForm(viewModel) {
          Toast.makeText(navController.context, "Clicked!", Toast.LENGTH_LONG).show()
          viewModel.setMatch()
          navController.navigate(MatchScreen.HOLE.name)
        }
      }
      composable(route = MatchScreen.HOLE.name) {
        holeScoring()
      }
    }
  })
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
fun MatchSetupForm(
  viewModel: MatchViewModel,
  onStartMatchClicked: () -> Unit
) {

  val player1 by rememberSaveable {
    mutableStateOf(Player())
  }

  val player2 by rememberSaveable {
    mutableStateOf(Player())
  }
  val player3 by rememberSaveable {
    mutableStateOf(Player())
  }
  val player4 by rememberSaveable {
    mutableStateOf(Player())
  }
  Column(modifier = Modifier.padding(12.dp)) {
    Text("Team 1")
    TeamEntry(player1, player2)
    Spacer(modifier = Modifier.height(48.dp))
    Text("Team 2")
    TeamEntry(player3, player4)
    Spacer(modifier = Modifier.weight(1.0f))
    Button(modifier = Modifier
      .fillMaxWidth()
      .height(48.dp),
           content = { Text(text = "Start Match") },
           onClick = {
             onStartMatchClicked()
           })
  }
}

@Composable
fun TeamEntry(player1: Player, player2: Player) {
  PlayerEntry(player1)
  PlayerEntry(player2)
}


@Composable
fun PlayerEntry(player: Player) {
  Row {
    OutlinedTextField(value = "",
                      onValueChange = {
                        player = Player(name = it)
                      },
                      label = { Text("Name") })
    Spacer(modifier = Modifier.width(12.dp))
    OutlinedTextField(
      value = "",
      onValueChange = {
        player = Player(hcp = it.toInt())
      },
      label = {
        Text("HCP")
      }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
  }
}

@Preview
@Composable
fun holeScoring() {
  Column(modifier = Modifier.padding(12.dp)) {
    Text(text = "Hole : 1   HCP: 11   Par 4")
    Spacer(modifier = Modifier.height(12.dp))
    Row(verticalAlignment = Alignment.CenterVertically) {
      Text(text = "Name: Player Name")
      Spacer(modifier = Modifier.width(12.dp))
      OutlinedTextField(
        value = "",
        onValueChange = {},
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
      )
    }
  }
}

