package com.example.hi_lo.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.hi_lo.data.MatchScreen.SCORE
import com.example.hi_lo.data.MatchScreen.SUMMARY
import com.example.hi_lo.data.MatchViewModel
import com.example.hi_lo.data.course

@Composable
fun EnterScore(viewModel: MatchViewModel, navController: NavHostController) {
  val hole = course[viewModel.hole.value!! - 1]
  Column(
    modifier = Modifier.padding(12.dp),
    verticalArrangement = Arrangement.Center
  ) {

    PlayerScore(viewModel.team1.golfer1, hole.second)
    PlayerScore(viewModel.team1.golfer2, hole.second)

    Row(
      Modifier.height(40.dp),
      verticalAlignment = Alignment.CenterVertically
    ) {
      Divider(
        color = Color.DarkGray, modifier = Modifier
          .height(2.dp)
      )
    }

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
             //TODO do actual point entries
             viewModel.addPointsToTeam1Score(1)
             viewModel.addPointsToTeam2Score(2)
             if (viewModel.hasNextHole()) {
               viewModel.nextHole()
               navController.navigate(SCORE.name)
             } else {
               navController.navigate(SUMMARY.name)
             }
           })
  }
}
