package com.hi_lo.ui

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.ExposedDropdownMenuDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp


@Composable
fun CourseSelection(onSetupClicked: () -> Unit) {
    Column(modifier = Modifier.padding(8.dp)) {
        Text("Select a course: ")
        Spacer(modifier = Modifier.height(20.dp))
        CourseSelectDropdown()
        Spacer(modifier = Modifier.height(20.dp))
        Button(modifier = Modifier.fillMaxWidth().height(48.dp),
            onClick = {
                onSetupClicked()
            }) {
            Text(text = "Setup Match")
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun CourseSelectDropdown() {
    val context = LocalContext.current
    val courses = arrayOf("Augusta", "Torrey Pines", "St. Andrews", "Maple Creek")
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(courses[0]) }
    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            }
        ) {
            TextField(
                value = selectedText,
                onValueChange = {},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = Modifier
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                courses.forEach { item ->
                    DropdownMenuItem(
                        content = { Text(text = item) },
                        onClick = {
                            selectedText = item
                            expanded = false
                            Toast.makeText(context, item, Toast.LENGTH_SHORT).show()
                        }
                    )
                }
            }
        }
    }
}