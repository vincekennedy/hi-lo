package com.example.hi_lo.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.hi_lo.data.MatchScreen
import com.example.hi_lo.data.MatchViewModel


@Composable
fun SettleScreen(matchViewModel: MatchViewModel, navController: NavHostController) {

    Column(modifier = Modifier.padding(12.dp)) {
        Text(text = "Settle Up ${matchViewModel.team1Score.value} \t ${matchViewModel.team2Score.value}")
        Button(modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
            content = { Text(text = "Start New Match") },
            onClick = {
                matchViewModel.resetMatch()
                navController.navigate(MatchScreen.START.name)
            })
    }
}
