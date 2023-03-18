package com.example.hi_lo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun HoleScoring(matchViewModel: MatchViewModel) {
  val hole = course[matchViewModel.hole.value!! - 1]
  Column(modifier = Modifier.padding(12.dp)) {
    Text(text = "HCP: ${hole.second}   Par ${hole.third}")
    Spacer(modifier = Modifier.height(12.dp))
    PlayerScore(matchViewModel.team1.golfer1, hole.second)
    PlayerScore(matchViewModel.team1.golfer2, hole.second)
    Spacer(modifier = Modifier.height(48.dp))
    PlayerScore(matchViewModel.team2.golfer1, hole.second)
    PlayerScore(matchViewModel.team2.golfer2, hole.second)
    Spacer(modifier = Modifier.weight(1.0f))
    Button(modifier = Modifier
      .fillMaxWidth()
      .height(48.dp),
           content = { Text(text = "Next Hole") },
           onClick = {
             matchViewModel.addPointsToTeam1Score(1)
             matchViewModel.addPointsToTeam2Score(2)
             matchViewModel.nextHole()
           })
  }
}

@Composable
fun PlayerScore(golfer: Golfer, hcp: Int) {
  val background = if (golfer.hcp >= hcp) {
    Color.Green
  } else {
    Color.Transparent
  }
  Row(
    modifier = Modifier.padding(4.dp).background(background),
    verticalAlignment = Alignment.CenterVertically,
  ) {
    Text(
      text = "${golfer.name} (${golfer.hcp})",
    )
    Spacer(modifier = Modifier.width(12.dp))
    OutlinedTextField(
      value = "",
      label = { Text("Score") },
      onValueChange = {
      },
      keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
  }
}