package com.hi_lo.data

import androidx.lifecycle.MutableLiveData
import com.hi_lo.data.retrofit.ApiClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class CoursesViewModel {
    var courses: MutableLiveData<Course> = MutableLiveData(null)

    fun fetchCourses(): MutableLiveData<Course> {
        CoroutineScope(Dispatchers.IO).launch {
            val c = ApiClient.courseService.getCourses()

            Timber.e("Courses: $c")
        }
        return courses
    }
}