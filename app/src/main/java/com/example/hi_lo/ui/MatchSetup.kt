package com.example.hi_lo.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.hi_lo.data.Golfer
import com.example.hi_lo.data.MatchScreen.SCORE
import com.example.hi_lo.data.MatchViewModel
import com.example.hi_lo.data.Team

@Composable
fun SetupMatch(viewModel: MatchViewModel, navController: NavHostController) {
  val name1 = remember { mutableStateOf("") }
  val hcp1 = remember { mutableStateOf("") }
  val name2 = remember { mutableStateOf("") }
  val hcp2 = remember { mutableStateOf("") }
  val name3 = remember { mutableStateOf("") }
  val hcp3 = remember { mutableStateOf("") }
  val name4 = remember { mutableStateOf("") }
  val hcp4 = remember { mutableStateOf("") }

  val enableStart =
    name1.value.isNotEmpty() && name2.value.isNotEmpty()
        && name3.value.isNotEmpty() && name4.value.isNotEmpty()
        && hcp1.value.isNotEmpty() && hcp2.value.isNotEmpty()
        && hcp3.value.isNotEmpty() && hcp4.value.isNotEmpty()

  Column(modifier = Modifier.padding(8.dp)) {
    Text("Team 1")
    EnterGolfer(name = name1, handicap = hcp1)
    EnterGolfer(name = name2, handicap = hcp2)
    Spacer(modifier = Modifier.height(24.dp))
    Text("Team 2")
    EnterGolfer(name = name3, handicap = hcp3)
    EnterGolfer(name = name4, handicap = hcp4)
    Spacer(modifier = Modifier.weight(1.0f))
    Button(
      modifier = Modifier
        .fillMaxWidth()
        .height(48.dp),
      onClick = {
        viewModel.startMatch(
          Team(
            Golfer(name1.value, hcp1.value.toInt()),
            Golfer(name2.value, hcp2.value.toInt())
          ),
          Team(
            Golfer(name3.value, hcp3.value.toInt()),
            Golfer(name4.value, hcp4.value.toInt())
          )
        )
        navController.navigate(SCORE.name)
      },
      enabled = enableStart
    ) {
      Text(text = "Start Match")
    }
  }

}