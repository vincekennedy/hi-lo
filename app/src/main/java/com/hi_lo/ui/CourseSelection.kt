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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.MutableLiveData
import com.hi_lo.data.Course
import com.hi_lo.data.CoursesViewModel
import com.hi_lo.data.MatchViewModel


@Composable
fun CourseSelection(matchViewModel: MatchViewModel, onSetupClicked: () -> Unit) {
    val courseViewModel = CoursesViewModel()
    LaunchedEffect(key1 = "onLaunch") {
        courseViewModel.fetchCourses()
    }
    Column(modifier = Modifier.padding(8.dp)) {
        Text("Select a course: ")
        Spacer(modifier = Modifier.height(20.dp))
        CourseSelectDropdown(matchViewModel, courseViewModel.courses)
        Spacer(modifier = Modifier.height(20.dp))
        Button(modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
            onClick = {
                onSetupClicked()
            }) {
            Text(text = "Setup Match")
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun CourseSelectDropdown(matchViewModel: MatchViewModel, courses: MutableLiveData<List<Course>>) {
    val context = LocalContext.current
    var expanded by remember { mutableStateOf(false) }
    var selectedCourse by remember { mutableStateOf(courses.value?.first()) }
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
                value = selectedCourse?.name ?: "Choose a Course...",
                onValueChange = { },
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = Modifier
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                courses.value?.forEach { item ->
                    DropdownMenuItem(
                        content = { Text(text = item.name) },
                        onClick = {
                            selectedCourse = item
                            matchViewModel.selectCourse(item)
                            expanded = false
                            Toast.makeText(context, item.name, Toast.LENGTH_SHORT).show()
                        }
                    )
                }
            }
        }
    }
}