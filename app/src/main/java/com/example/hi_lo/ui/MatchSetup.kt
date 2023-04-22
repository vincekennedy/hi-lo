package com.example.hi_lo.ui

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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import androidx.navigation.NavHostController
import com.example.hi_lo.data.Golfer
import com.example.hi_lo.data.MatchScreen.SCORE
import com.example.hi_lo.data.MatchViewModel
import com.example.hi_lo.data.Team

@Composable
fun SetupMatch(viewModel: MatchViewModel, navController: NavHostController) {
  val golfer1Name = remember { mutableStateOf("") }
  val golfer1Hcp = remember { mutableStateOf("") }
  val golfer2Name = remember { mutableStateOf("") }
  val golfer2Hcp = remember { mutableStateOf("") }
  val golfer3Name = remember { mutableStateOf("") }
  val golfer3Hcp = remember { mutableStateOf("") }
  val golfer4Name = remember { mutableStateOf("") }
  val golfer4Hcp = remember { mutableStateOf("") }

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
             navController.navigate(SCORE.name)
           }) {
      Text(text = "Start Match")
    }
  }
}

@Composable
private fun EnterGolfer(
  name: MutableState<String>,
  handicap: MutableState<String>
) {
  Row(modifier = Modifier.padding(4.dp)) {
    OutlinedTextField(value = name.value,
                      label = { Text("Name") },
                      singleLine = true,
                      onValueChange = {
                        name.value = it
                      }
    )
    Spacer(modifier = Modifier.width(12.dp))
    OutlinedTextField(
      value = handicap.value,
      label = { Text("HCP") },
      singleLine = true,
      onValueChange = {
        if (it.isEmpty()){
          handicap.value = ""
        }
        if (it.isNotEmpty() && it.isDigitsOnly() && it.toInt() <= 36) {
          handicap.value = it
        }
      },
      keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
  }
}
