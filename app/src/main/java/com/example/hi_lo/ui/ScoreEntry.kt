package com.example.hi_lo.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize.Min
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
import androidx.compose.material.Divider
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import androidx.navigation.NavHostController
import com.example.hi_lo.data.Golfer
import com.example.hi_lo.data.MatchScreen.SCORE
import com.example.hi_lo.data.MatchScreen.SUMMARY
import com.example.hi_lo.data.MatchViewModel
import com.example.hi_lo.data.course


@Composable
fun EnterScore(matchViewModel: MatchViewModel, navController: NavHostController) {
  val hole = course[matchViewModel.hole.value!! - 1]
  val showConfirmation = remember { mutableStateOf(false) }
  val p1 = remember { mutableStateOf(0) }
  val p2 = remember { mutableStateOf(0) }
  val p3 = remember { mutableStateOf(0) }
  val p4 = remember { mutableStateOf(0) }

  val lowTeam = remember { mutableStateOf(0) }
  val highTeam = remember { mutableStateOf(20) }

  Column(
    modifier = Modifier.padding(12.dp),
    verticalArrangement = Arrangement.Center
  ) {

    PlayerScore(matchViewModel.team1.golfer1, hole.second, p1)
    PlayerScore(matchViewModel.team1.golfer2, hole.second, p2)

    Row(
      Modifier.height(20.dp),
      verticalAlignment = Alignment.CenterVertically
    ) {
      Divider(
        color = Color.DarkGray, modifier = Modifier
          .height(2.dp)
      )
    }

    PlayerScore(matchViewModel.team2.golfer1, hole.second, p3)
    PlayerScore(matchViewModel.team2.golfer2, hole.second, p4)

    Spacer(modifier = Modifier.weight(1.0f))
    Button(modifier = Modifier
      .fillMaxWidth()
      .height(48.dp),
           content = {
             if (matchViewModel.hasNextHole()) {
               Text(text = "Next Hole")
             } else {
               Text(text = "Finish")
             }
           },
           onClick = {
             showConfirmation.value = true

             val scores =
               scoreHole(matchViewModel, listOf(p1.value, p2.value), listOf(p3.value, p4.value))
             lowTeam.value = scores.first
             highTeam.value = scores.second
           })

    if (showConfirmation.value) {
      AlertDialog(
        title = { Text(text = "Confirm Scores") },
        text = {
          Column {
            Text(text = "Low Team : ${lowTeam.value}")
            Text(text = "High Team : ${highTeam.value}")
          }
        },
        onDismissRequest = {
          showConfirmation.value = false
        },
        confirmButton = {
          Button(
            onClick = {
              showConfirmation.value = false
              if (matchViewModel.hasNextHole()) {
                matchViewModel.nextHole()
                navController.navigate(SCORE.name)
              } else {
                navController.navigate(SUMMARY.name)
              }
            }
          ) {
            Text("Confirm")
          }
        },
        dismissButton = {
          Button(
            onClick = {
              showConfirmation.value = false
            }
          ) {
            Text("Cancel")
          }
        }
      )
    }
  }
}

fun scoreHole(
  matchViewModel: MatchViewModel,
  team1: List<Int>,
  team2: List<Int>,
): Pair<Int, Int> {

  var lowTeam = 0
  var highTeam = 0

  val low1 = team1.min()
  val low2 = team2.min()
  val high1 = team1.max()
  val high2 = team2.max()

  if (low1 != low2) {
    lowTeam = if (low1 < low2) {
      matchViewModel.addPointsToTeam1Score(1)
      1
    } else {
      matchViewModel.addPointsToTeam2Score(1)
      2
    }
  }

  if (high1 != high2) {
    highTeam = if (high1 < high2) {
      matchViewModel.addPointsToTeam1Score(1)
      2
    } else {
      matchViewModel.addPointsToTeam2Score(1)
      1
    }
  }

  return Pair(lowTeam, highTeam)

}

@Composable
fun PlayerScore(golfer: Golfer, hcp: Int, playerScore: MutableState<Int>) {

 val strokes = if (golfer.hcp >= hcp) {
    if (golfer.hcp.minus(18).minus(hcp) > 0) {
      2
    } else {
      1
    }
  } else {
    0
  }

  val background = when (strokes) {
    1 -> { Color.Green }
    2 -> { Color.Yellow }
    else -> { Color.Transparent }
  }

  val score = remember { mutableStateOf("") }

  Row(verticalAlignment = Alignment.CenterVertically) {
    Text(
      text = "${golfer.name} (${golfer.hcp})",
      modifier = Modifier
        .background(background),
    )
    Spacer(modifier = Modifier.width(12.dp))
    OutlinedTextField(
      value = score.value,
      label = { Text("Score") },
      onValueChange = {
        if (it.isEmpty()) {
          score.value = ""
        } else if (it.isDigitsOnly() && it.toInt() <= 9) {
          score.value = it
          playerScore.value = it.toInt().minus(strokes)
        }
      },
      keyboardOptions = KeyboardOptions(
        keyboardType = KeyboardType.Number,
        imeAction = ImeAction.Next
      )
    )
  }
  PointEntry()
}

@Composable
fun PointEntry() {
  Row {
    LabeledCheckbox(label = "First")
    LabeledCheckbox(label = "Closest")
    LabeledCheckbox(label = "Sneaky")
    LabeledCheckbox(label = "Pully")
    LabeledCheckbox(label = "Sandy")
  }
}

@Composable
fun LabeledCheckbox(label: String) {
  val isChecked = remember { mutableStateOf(false) }

  Column(
    modifier = Modifier
      .width(Min)
      .padding(4.dp)
  ) {
    Text(text = label)
    Checkbox(checked = isChecked.value, onCheckedChange = {
      isChecked.value = it
    })
  }
}


