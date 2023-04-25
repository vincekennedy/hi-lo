package com.example.hi_lo.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize.Min
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Checkbox
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.hi_lo.data.Golfer

@Composable
fun PlayerScore(golfer: Golfer, hcp: Int) {
  val background = if (golfer.hcp >= hcp) {
    Color.Green
  } else {
    Color.Transparent
  }
  Row(
    modifier = Modifier
      .background(background),
    verticalAlignment = Alignment.CenterVertically,
  ) {
    Text(text = "${golfer.name} (${golfer.hcp})")
    Spacer(modifier = Modifier.width(12.dp))
    OutlinedTextField(
      value = "",
      label = { Text("Score") },
      onValueChange = { },
      keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
  }
  PointEntry()
}

@Composable
fun PointEntry() {
  Row {
    LabeledCheckbox(label = "Closest")
    LabeledCheckbox(label = "Greeny")
    LabeledCheckbox(label = "Sneaky")
    LabeledCheckbox(label = "Sandy")
    LabeledCheckbox(label = "Pully")
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