package com.hi_lo.data

import androidx.lifecycle.MutableLiveData
import com.hi_lo.data.retrofit.ApiClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CoursesViewModel {
    var courses: MutableLiveData<List<Course>> = MutableLiveData(null)

    fun fetchCourses(): MutableLiveData<List<Course>> {
        CoroutineScope(Dispatchers.IO).launch {
            courses.postValue(ApiClient.courseService.getCourses())
        }
        return courses
    }
}