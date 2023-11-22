package com.example.hi_lo.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.hi_lo.data.MatchScreen
import com.example.hi_lo.data.MatchViewModel

@Composable
fun ScoringSummary(
    matchViewModel: MatchViewModel,
    navController: NavHostController
) {
    Column(modifier = Modifier.padding(12.dp)) {
        Text(text = "Summary View", modifier = Modifier.size(20.dp) )
        Text(text = "Team 1: ${matchViewModel.team1Score.value.toString()}")
        Text(text = "Team 2: ${matchViewModel.team2Score.value.toString()}")
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